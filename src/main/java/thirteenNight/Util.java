package thirteenNight;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static Location getBehindLocation(Location loc) {
        float ang = loc.getYaw() + 90;

        if (ang < 0) ang += 360;

        double newX = Math.cos(Math.toRadians(ang));
        double newZ = Math.sin(Math.toRadians(ang));

        return new Location(loc.getWorld(), loc.getX() - newX, loc.getY(), loc.getZ() - newZ, loc.getYaw(), loc.getPitch());
    }

    public static Location multiply(Location loc, double x, double y, double z) {
        double pitch = ((loc.getPitch() + 90) * Math.PI) / 180D;
        double yaw = ((loc.getYaw() + 90) * Math.PI) / 180D;
        return new Location(loc.getWorld(), x * Math.sin(pitch) * Math.cos(yaw), y * Math.cos(pitch), z * Math.sin(pitch) * Math.sin(yaw));
    }

    public static List<Vector> circle(double xSize, double zSize, double height) {
        List<Vector> result = new ArrayList<>();
        for (double degree = 0; degree < 360; degree++) {
            double radians = Math.toRadians(degree);
            double x = Math.cos(radians) * xSize;
            double z = Math.sin(radians) * zSize;
            result.add(new Vector(x, height, z));
        }

        return result;
    }
}
