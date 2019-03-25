# PixLedServer :sheep: :rainbow:
General purpose Spring server to control connected PixLed devices.

This server can be used with ![PixLed Androïd](https://github.com/PaulBreugnot/PixLedAndroid) and ![PixLed ESP32 modules](https://github.com/PaulBreugnot/PixLedModule_Strip) to build an awesome connected LED strip lighting system!

# Features
- [x] Devices and device groups persistent storage and management
- [x] Color changes and switches using a REST API
- [x] Commands transmission to devices using MQTT
- [x] Device group controls (switch only)
- [ ] Animation generation
- [ ] Animation streaming to devices
- [ ] Other devices handling (panels and so on)

# Install
The following tutorial will teach you how to setup the PixLedServer on a Raspberry Pi. However, the same installation can be performed on most of the common linux distributions with minor changed.

## Set up your Raspberry Pi
1. Install your raspberry pi with the Raspbian OS (check the [RPi documentation](https://projects.raspberrypi.org/en/projects/raspberry-pi-getting-started) to learn how to)
2. Connect the Raspberry Pi to the local network, via Ethernet or WiFi.

All the following commands can be run from terminals on the Pi, or from a SSH connection, as you prefer.

Don't forget to launch
```
sudo apt-get update
sudo apt-get dist-upgrade
```
before installing the following packaged.

## Note about mDNS
For mysterious reasons, mDNS doesn't seem to work well when the Raspberry Pi / the server is connected on your local network using ethernet. So please prefer a wifi connection.

However, notice that its usage is not mandatory. The RPi / server IP can also be configured manually on each PixLedDevice and in the PixLed app.

## Avahi
[Avahi](https://en.wikipedia.org/wiki/Avahi_(software)) is a software that we allow devices (including led devices and the Androïd devices) to automatically find the MQTT broker and the HTTP server on your local network using mDNS.

### Download
To download the `avahi-daemon` package, run :
```
sudo apt-get install avahi-daemon
```

### Configure
Now, you need to configure Avahi so that he will broadcast our services adresses on your local network.
To do so, you need to create the two following files in the `/etc/avahi/services` folder:

`sudo nano /etc/avahi/services/pixledserver.service` :
```
<?xml version="1.0" standalone='no'?>
<!DOCTYPE service-group SYSTEM "avahi-service.dtd">
<service-group>
  <name>PixLedServer</name>
  <service protocol="ipv4">
    <type>_http._tcp</type>
    <port>8080</port>
  </service>
</service-group>
```

`sudo nano /etc/avahi/services/pixledbroker.service` :
```
<?xml version="1.0" standalone='no'?>
<!DOCTYPE service-group SYSTEM "avahi-service.dtd">
<service-group>
 <name>PixLedBroker</name>
  <service protocol="ipv4">
   <type>_mqtt._tcp</type>
   <port>1883</port>
  </service>
</service-group>
```

### Run and enable
The Avahi daemon can be launched running `sudo sytemctl start avahi-daemon`.
You can then check that everything work with `sudo systemctl status avahi-daemon`.

Finally, run `sudo systemctl enable avahi-daemon`.
This will make the Avahi service to start each time you boot your system.

Optionnally, you can check that your services are well broadcast with the `avahi-browse` tool. If you want to do so, check how to install it depending on your OS.
You can launch this tool from any device connected to your local network.
To test directly from the RPi (and Debian, Ubuntu...) :
```
sudo apt-get install avahi-utils
```
And then `avahi-browse _http._tcp` should output :
```
+  wlan0 IPv4 PixLedServer                                  Web Site             local
```
And for `avahi-browse _mqtt._tcp` :
```
+  wlan0 IPv4 PixLedBroker                                  _mqtt._tcp           local
```

## Mosquitto
[Mosquitto](https://mosquitto.org/) is an open-source software that implements the MQTT protocol. We will use it to install the MQTT broker, that is used to transmit messages such as devices switchs, color changes, and connection / disconnection.

### Download
To download the `mosquitto` package, run :
```
sudo apt-get install mosquitto
```

### Configure
Run `sudo nano /etc/mosquitto/mosquitto.conf` and append the following lines to the file :
```
allow_anonymous true
acl_file /etc/mosquitto/mosquitto.acl

listener 1883
protocol mqtt

listener 9001
protocol websockets
```

Run `sudo nano /etc/mosquitto/mosquitto.acl` and copy / paste the following content :
```
#Devices switch
topic readwrite /devices/+/state/switch

#Devices color
topic readwrite /devices/+/state/color

#Device groups switch
topic readwrite /groups/+/state/switch

#Light connection status topic
topic /connected
topic /disconnected
topic /check
```

This last file defines the topics that will be used for publishing / subscribing to data in our system.

### Run and enable
As with Avahi, you can launch the Mosquitto MQTT broker with `sudo systemctl start mosquitto` and check its status with `sudo systemctl status mosquitto`.

To enable it at boot, run `sudo systemctl enable mosquitto`.

## PixLed server
The PixLed server is what corresponds to this repository.

### Dowload
You can download the compiled .jar file from the [releases tabs](https://github.com/PixLed/PixLedServer/releases) or directly running
```
wget https://github.com/PixLed/PixLedServer/releases/download/v1.0.0/pixledserver-1.0-SNAPSHOT.jar
mkdir ~/pixled
mv pixledserver-1.0-SNAPSHOT.jar ~/pixled
```

### Run and enable
This part requires extra steps, because this time you need to create your own systemd service file that will allow you to run the .jar as a service, for example to enable it at boot.

To do so, run `sudo nano /etc/systemd/system/pixledserver.service` and copy / paste the following content :
```
[Unit]
Description=PixLed Server
After=network.target

[Service]
ExecStart=/usr/bin/java -jar pixledserver-1.0-SNAPSHOT.jar
WorkingDirectory=/home/pi/pixled
StandardOutput=inherit
StandardError=inherit
Restart=always
User=pi

[Install]
WantedBy=multi-user.target
```
**Don't forget to modify the User field if you modified it.**

You can now run the `pixledserver` with `sudo systemctl start pixledserver`, check its status with `sudo systemctl status pixledserver` and above all enable it at boot with `sudo systemctl enable pixledserver`.


# You're done!
Now just run `sudo reboot` to reboot your Pi. All the services should be launched at boot, and you can always check their status using `sudo systemctl status` commands.
Please note that when you shutdown the pi unplugging it, some issues may occur at boot with some component. So be sure to cleanly boot the RPi using `sudo reboot` when everything is set up.

# App and modules
If not done yet, you can now connect your ![PixLed Androïd app](https://github.com/PaulBreugnot/PixLedAndroid) and ![PixLed modules](https://github.com/PaulBreugnot/PixLedModule_Strip) to your server! :sheep: :rainbow:

# LICENSE
This software and all the PixLed tools are released under the [GNU General Public License v3.0](https://github.com/PixLed/PixLedServer/blob/master/LICENSE).
