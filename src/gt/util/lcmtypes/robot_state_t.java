/* LCM type definition class file
 * This file was automatically generated by lcm-gen
 * DO NOT MODIFY BY HAND!!!!
 */

package gt.util.lcmtypes;
 
import java.io.*;
import java.util.*;
import lcm.lcm.*;
 
public final class robot_state_t implements lcm.lcm.LCMEncodable
{
    public float robo_x;
    public float robo_y;
    public float robo_vx;
    public float robo_vy;
 
    public robot_state_t()
    {
    }
 
    public static final long LCM_FINGERPRINT;
    public static final long LCM_FINGERPRINT_BASE = 0xbf6373da79c6e9a3L;
 
    static {
        LCM_FINGERPRINT = _hashRecursive(new ArrayList<Class<?>>());
    }
 
    public static long _hashRecursive(ArrayList<Class<?>> classes)
    {
        if (classes.contains(robot_state_t.class))
            return 0L;
 
        classes.add(robot_state_t.class);
        long hash = LCM_FINGERPRINT_BASE
            ;
        classes.remove(classes.size() - 1);
        return (hash<<1) + ((hash>>63)&1);
    }
 
    public void encode(DataOutput outs) throws IOException
    {
        outs.writeLong(LCM_FINGERPRINT);
        _encodeRecursive(outs);
    }
 
    public void _encodeRecursive(DataOutput outs) throws IOException
    {
        outs.writeFloat(this.robo_x); 
 
        outs.writeFloat(this.robo_y); 
 
        outs.writeFloat(this.robo_vx); 
 
        outs.writeFloat(this.robo_vy); 
 
    }
 
    public robot_state_t(byte[] data) throws IOException
    {
        this(new LCMDataInputStream(data));
    }
 
    public robot_state_t(DataInput ins) throws IOException
    {
        if (ins.readLong() != LCM_FINGERPRINT)
            throw new IOException("LCM Decode error: bad fingerprint");
 
        _decodeRecursive(ins);
    }
 
    public static robot_state_t _decodeRecursiveFactory(DataInput ins) throws IOException
    {
        robot_state_t o = new robot_state_t();
        o._decodeRecursive(ins);
        return o;
    }
 
    public void _decodeRecursive(DataInput ins) throws IOException
    {
        this.robo_x = ins.readFloat();
 
        this.robo_y = ins.readFloat();
 
        this.robo_vx = ins.readFloat();
 
        this.robo_vy = ins.readFloat();
 
    }
 
    public robot_state_t copy()
    {
        robot_state_t outobj = new robot_state_t();
        outobj.robo_x = this.robo_x;
 
        outobj.robo_y = this.robo_y;
 
        outobj.robo_vx = this.robo_vx;
 
        outobj.robo_vy = this.robo_vy;
 
        return outobj;
    }
 
}

