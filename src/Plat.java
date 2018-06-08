import java.awt.*;

public class Plat extends Rectangle {
    int x = 160;
    int y = 245;
    int width  = 40;
    int height = 5;

   /* public void setPosXPlat(int posXPlat) {
        this.posXPlat = posXPlat;
    }

    public void setPosYPlat(int posYPlat) {
        this.posYPlat = posYPlat;
    }

    public void setPlatWidth(int platWidth) {
        this.posXPlat = posXPlat;
    }

    public void setPlatHeight(int platHeight) {
        this.platHeight = platHeight;
    }


    public int getPosXPlat() {
        return posXPlat;
    }
    public int getPosYPlat() {
        return posYPlat;
    }
    public int getPlatHeight() {
        return platHeight;
    }
    public int getPlatWidth() {
        return platWidth;
    }

public Plat(int posXPlat, int posYPlat, int platWidth, int platHeight) {
        this.posXPlat = posXPlat;
        this.posYPlat = posYPlat;
        this.platWidth = platWidth;
        this.platHeight = platHeight;
    }

    */
   public Plat(int x, int y, int width, int height) {
       this.x = x;
       this.y = y;
       this.width = width;
       this.height = height;
   }


    @Override
    public boolean intersects(Rectangle r) {
        int tw = this.width;
        int th = this.height;
        int rw = r.width;
        int rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.x;
        int ty = this.y;
        int rx = r.x;
        int ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public boolean intersects(Globe r) {
        int tw = this.width;
        int th = this.height;
        int rw = r.width;
        int rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.x;
        int ty = this.y;
        int rx = r.x;
        int ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

}
