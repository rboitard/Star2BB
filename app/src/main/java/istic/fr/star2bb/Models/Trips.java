package istic.fr.star2bb.Models;

/**
 * Created by Bah on 18/11/2017.
 */

public class Trips {

    private int RouteId;
    private int serviceId;
    private String headSign;
    private  int directionId;
    private int blockId;
    private String wheelchairAccessible;
    private  int _ID;

    public Trips( int routeId, int serviceId, String headSign, int directionId, int blockId, String wheelchairAccessible) {
        RouteId = routeId;
        this.serviceId = serviceId;
        this.headSign = headSign;
        this.directionId = directionId;
        this.blockId = blockId;
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public Trips(int _ID, int routeId, int serviceId, String headSign, int directionId, int blockId, String wheelchairAccessible) {
        RouteId = routeId;
        this.serviceId = serviceId;
        this.headSign = headSign;
        this.directionId = directionId;
        this.blockId = blockId;
        this.wheelchairAccessible = wheelchairAccessible;
        this._ID = _ID;
    }

    public int getRouteId() {
        return RouteId;
    }

    public void setRouteId(int routeId) {
        RouteId = routeId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getHeadSign() {
        return headSign;
    }

    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    public int getDirectionId() {
        return directionId;
    }

    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public String getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(String wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    @Override
    public String toString() {
        return "Trips{" +
                "RouteId=" + RouteId +
                ", serviceId=" + serviceId +
                ", headSign='" + headSign + '\'' +
                ", directionId=" + directionId +
                ", blockId=" + blockId +
                ", wheelchairAccessible='" + wheelchairAccessible + '\'' +
                ", _ID=" + _ID +
                '}';
    }
}
