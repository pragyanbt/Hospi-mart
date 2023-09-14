public class MedicineOrder {
    String medId;
    String medQty;

    public MedicineOrder(String medId, String medQty) {
        this.medId = medId;
        this.medQty = medQty;
    }

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public String getMedQty() {
        return medQty;
    }

    public void setMedQty(String medQty) {
        this.medQty = medQty;
    }
}

