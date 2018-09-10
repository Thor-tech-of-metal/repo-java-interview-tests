package com.thor.tech.file.paser.model;

public class DataModel {

    private final String name;

    public String getSurname() {
        return surname;
    }

    private final String surname;

    public DataModel(String name, String surname){
        this.name =name;
        this.surname =surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataModel)) return false;

        DataModel dataModel = (DataModel) o;

        if (getName() != null ? !getName().equals(dataModel.getName()) : dataModel.getName() != null) return false;
        return getSurname() != null ? getSurname().equals(dataModel.getSurname()) : dataModel.getSurname() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        return result;
    }
}
