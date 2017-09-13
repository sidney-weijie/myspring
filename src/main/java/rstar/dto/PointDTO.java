package rstar.dto;

public class PointDTO extends AbstractDTO{
    public long oid;
    public float[] coords;

    public PointDTO(long oid, float[] coords) {
        this.oid = oid;
        this.coords = coords;
    }
}
