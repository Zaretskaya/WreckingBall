import java.awt.*;

public class Block extends Rectangle {
    private int BlockX = 70;
    private int BlockY = 50;

    private int BlockWidth = 30;
    private int BlockHeight = 20;

    public int getBlockX() {
        return BlockX;
    }

    public int getBlockY() {
        return BlockY;
    }

    public int getBlockHeight() {
        return BlockHeight;
    }

    public int getBlockWidth() {
        return BlockWidth;
    }

    public void setBlockX(int blockX) {
        BlockX = blockX;
    }

    public void setBlockY(int blockY) {
        BlockY = blockY;
    }

    public void setBlockHeight(int blockHeight) {
        BlockHeight = blockHeight;
    }

    public void setBlockWidth(int blockWidth) {
        BlockWidth = blockWidth;
    }


    public Block(int BlockX, int BlockY, int BlockWidth, int BlockHeight) {
        this.BlockX = BlockX;
        this.BlockY = BlockY;
        this.BlockWidth = BlockWidth;
        this.BlockHeight = BlockHeight;
    }

    @Override
    public boolean intersects(Rectangle r) {
        int tw = this.BlockWidth;
        int th = this.BlockHeight;
        int rw = r.width;
        int rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.BlockX;
        int ty = this.BlockY;
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
        int tw = this.BlockWidth;
        int th = this.BlockHeight;
        int rw = r.width;
        int rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.BlockX;
        int ty = this.BlockY;
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
