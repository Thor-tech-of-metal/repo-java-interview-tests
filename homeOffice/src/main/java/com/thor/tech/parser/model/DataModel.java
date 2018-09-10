package com.thor.tech.parser.model;

public class DataModel {

    public static String notFound= "Vehicle details could not be found";
    private final String plateNumber;
    private final String colour;
    private final String make;

    public DataModel(String plateNumber, String colour, String make) {
        this.plateNumber = plateNumber;
        this.colour = colour;
        this.make = make;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getColour() {
        return colour;
    }

    public String getMake() {
        return make;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataModel)) return false;

        DataModel dataModel = (DataModel) o;

        if (getPlateNumber() != null ? !getPlateNumber().equals(dataModel.getPlateNumber()) : dataModel.getPlateNumber() != null)
            return false;
        if (getColour() != null ? !getColour().equals(dataModel.getColour()) : dataModel.getColour() != null)
            return false;
        return getMake() != null ? getMake().equals(dataModel.getMake()) : dataModel.getMake() == null;
    }

    @Override
    public int hashCode() {
        int result = getPlateNumber() != null ? getPlateNumber().hashCode() : 0;
        result = 31 * result + (getColour() != null ? getColour().hashCode() : 0);
        result = 31 * result + (getMake() != null ? getMake().hashCode() : 0);
        return result;
    }
}
