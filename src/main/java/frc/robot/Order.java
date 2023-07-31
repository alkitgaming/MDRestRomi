package frc.robot;

import org.json.JSONObject;

public class Order
{
    private int row;
    private boolean leftSide;
    private int id;

    public Order(int row, int id, boolean leftSide)
    {
        this.row = row;
        this.leftSide = leftSide;
        this.id = id;
    }

    public int getRow()
    {
        return row;
    }

    public int getID()
    {
        return id;
    }

    public boolean isLeftSide()
    {
        return leftSide;
    }

    public String toJson()
    {
        JSONObject jo = new JSONObject();
        jo.append("side", (leftSide ? "a" : "b"));
        jo.append("rownum", row);
        jo.append("id", id);
        return jo.toString();
    }

    public static String orderToJson(int rownum, int id, boolean side)
    {
        JSONObject jo = new JSONObject();
        jo.append("side", side);
        jo.append("rownum", rownum);
        jo.append("id", id);
        return jo.toString();
    }

    @Override
    public String toString()
    {
        return "" + row + leftSide + id;
    }
}