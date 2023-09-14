public class MedicineData {
    String medName;
    String medPrice;

    public MedicineData(String medName, String medPrice) {
        this.medName = medName;
        this.medPrice = medPrice;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(String medPrice) {
        this.medPrice = medPrice;
    }
}

