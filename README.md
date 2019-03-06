# PixLedServer
General purpose Spring server to control connected PixLed devices.

This server can be used with ![PixLed Androïd](https://github.com/PaulBreugnot/PixLedAndroid) and ![PixLed ESP32 modules](https://github.com/PaulBreugnot/PixLedModule_Strip) to build an awesome connected LED strip lighting system!

# Install
The following tutorial will teach you how to setup the PixLedServer on a Raspberry Pi. However, the same installation can be performed on most of the common linux distributions with minor changed.

## Set up your Raspberry Pi
1. Install your raspberry pi with the Raspbian OS (check the [RPi documentation](https://projects.raspberrypi.org/en/projects/raspberry-pi-getting-started) to learn how to)
2. Connect the Raspberry Pi to the local network, via Ethernet or WiFi.

All the following commands can be run from terminals on the Pi, or from a SSH connection, as you prefer. 

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
`/etc/avahi/services/pixledserver.service` :
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

`/etc/avahi/services/pixledbroker.service` :
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

## Mosquitto
[Mosquitto](https://mosquitto.org/) is an open-source software that implements the MQTT protocol. We will use it to install the MQTT broker, that is used to transmit messages such as devices switchs, color changes, and connection / disconnection.

### Download
To download the `mosquitto` package, run :
```
sudo apt-get install mosquitto
```

### Configure
Append the following lines to the file `/etc/mosquitto/mosquitto.conf` :
```
allow_anonymous true
acl_file /etc/mosquitto/mosquitto_acl

listener 1883
protocol mqtt

listener 9001
protocol websockets
```

And create a file `/etc/mosquitto/mosquitto_acl` with the following content :
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
You can download the compiled .jar file from the [releases tabs](https://github.com/PixLed/PixLedServer/releases) or directly running `wget https://github.com/PixLed/PixLedServer/releases/download/v1.0.0/pixledserver-1.0-SNAPSHOT.jar`

Then, run `mkdir /home/pi/pixled` and copy the .jar file to the created folder.

### Run and enable
This part requires extra steps, because this time you need to create your own systemd service file that will allow you to run the .jar as a service, for example to enable it at boot.

To so, just create the `/etc/systemd/system/pixledserver.service` :
