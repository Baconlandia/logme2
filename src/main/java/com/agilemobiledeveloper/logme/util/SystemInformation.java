package com.agilemobiledeveloper.logme.util;


import java.io.File;
import java.io.Serializable;
import java.text.NumberFormat;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.Serializable;
import java.text.NumberFormat;



public class SystemInformation implements Serializable {

 Logger log = LoggerFactory.getLogger(SystemInformation.class);

    private static final long serialVersionUID = 6860871803067118728L;

    /**
     *
     */
    public SystemInformation() {
        super();
    }

    private static final String BREAK = " ";

    private static String findHostname() {
        String hostName = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            {
                while (interfaces.hasMoreElements()) {
                    NetworkInterface nic = interfaces.nextElement();
                    Enumeration<InetAddress> addresses = nic.getInetAddresses();
                    while (hostName == null && addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();
                        if (!address.isLoopbackAddress()) {
                            hostName = address.getHostName();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return hostName;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getOsInfo());
        sb.append(getMemInfo());
        sb.append(getDiskInfo());
        sb.append(  findHostname( )); 
        sb.append(new java.util.Date());

        sb.append(System.getProperty("user.name"));
        return sb.toString();
    }

    private String OSname() {
        return System.getProperty("os.name");
    }

    private String OSversion() {
        return System.getProperty("os.version");
    }

    private String OsArch() {
        return System.getProperty("os.arch");
    }

    private long getTotalMem() {
        return Runtime.getRuntime().totalMemory();
    }

    private long getUsedMem() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public String getMemInfo() {
        NumberFormat format = NumberFormat.getInstance();
        StringBuilder sb = new StringBuilder();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long allocatedMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        sb.append("Free memory: ");
        sb.append(format.format(freeMemory / 1024));
        sb.append(SystemInformation.BREAK);
        sb.append("Allocated memory: ");
        sb.append(format.format(allocatedMemory / 1024));
        sb.append(SystemInformation.BREAK);
        sb.append("Max memory: ");
        sb.append(format.format(maxMemory / 1024));
        sb.append(SystemInformation.BREAK);
        sb.append("Total free memory: ");
        sb.append(format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));
        sb.append(SystemInformation.BREAK);
        sb.append("Total memory: ");
        sb.append(format.format(getTotalMem()));
        sb.append(SystemInformation.BREAK);
        sb.append("Used memory: ");
        sb.append(format.format(getUsedMem()));
        return sb.toString();
    }

    public String getOsInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("OS: ");
        sb.append(OSname());
        sb.append(SystemInformation.BREAK);
        sb.append("Version: ");
        sb.append(OSversion());
        sb.append(SystemInformation.BREAK);
        sb.append(": ");
        sb.append(OsArch());
        sb.append(SystemInformation.BREAK);
        sb.append("Available processors (cores): ");
        sb.append(Runtime.getRuntime().availableProcessors());
        sb.append(SystemInformation.BREAK);
        return sb.toString();
    }

    public String getDiskInfo() {
        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();
        StringBuilder sb = new StringBuilder();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            sb.append("File system root: ");
            sb.append(root.getAbsolutePath());
            sb.append(SystemInformation.BREAK);
            sb.append("Total space (bytes): ");
            sb.append(root.getTotalSpace());
            sb.append(SystemInformation.BREAK);
            sb.append("Free space (bytes): ");
            sb.append(root.getFreeSpace());
            sb.append(SystemInformation.BREAK);
            sb.append("Usable space (bytes): ");
            sb.append(root.getUsableSpace());
            sb.append(SystemInformation.BREAK);
        }
        return sb.toString();
    }
}