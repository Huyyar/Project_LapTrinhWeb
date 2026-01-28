package vn.edu.hcmuaf.fit.project_ltweb.model;

import java.util.Objects;


public class Address {
    private int id;
    private int userId;

    private String recipientName;
    private String recipientPhone;

    private String province;       // Tỉnh/Thành phố (VD: "Thành phố Hồ Chí Minh")
    private String district;       // Quận/Huyện (VD: "Quận 1")
    private String ward;           // Phường/Xã (VD: "Phường Bến Nghé")
    // Thông tin địa chỉ - Code (dùng để tích hợp API )
    private String provinceCode;   // Mã tỉnh (VD: "79")
    private String districtCode;   // Mã quận (VD: "760")
    private String wardCode;       // Mã phường (VD: "26734")


    private String addressDetail;
    private boolean defaultAddress;

    public Address() {
    }

    /**
     * Constructor đầy đủ (không bao gồm id và timestamps)
     * Dùng khi INSERT mới address
     */
    public Address(int userId, String recipientName, String recipientPhone,
                   String province, String district, String ward,
                   String provinceCode, String districtCode, String wardCode,
                   String addressDetail, boolean defaultAddress) {
        this.userId = userId;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.provinceCode = provinceCode;
        this.districtCode = districtCode;
        this.wardCode = wardCode;
        this.addressDetail = addressDetail;
        this.defaultAddress = defaultAddress;
    }

    /**
     * Constructor đầy đủ (bao gồm id)
     * Dùng khi SELECT từ database
     */
    public Address(int id, int userId, String recipientName, String recipientPhone,
                   String province, String district, String ward,
                   String provinceCode, String districtCode, String wardCode,
                   String addressDetail, boolean defaultAddress
                ) {
        this.id = id;
        this.userId = userId;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.provinceCode = provinceCode;
        this.districtCode = districtCode;
        this.wardCode = wardCode;
        this.addressDetail = addressDetail;
        this.defaultAddress = defaultAddress;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }





    /**
     * Lấy địa chỉ đầy đủ để hiển thị
     * (VD: "123 Đường ABC, Phường Bến Nghé, Quận 1, TP.HCM")
     */
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();

        if (addressDetail != null && !addressDetail.isEmpty()) {
            sb.append(addressDetail);
        }

        if (ward != null && !ward.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(ward);
        }

        if (district != null && !district.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(district);
        }

        if (province != null && !province.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(province);
        }

        return sb.toString();
    }

    /**
     * Kiểm tra địa chỉ có đầy đủ thông tin không
     *  true nếu đủ thông tin, false nếu thiếu
     */
    public boolean isComplete() {
        return recipientName != null && !recipientName.isEmpty()
                && recipientPhone != null && !recipientPhone.isEmpty()
                && province != null && !province.isEmpty()
                && district != null && !district.isEmpty()
                && ward != null && !ward.isEmpty()
                && addressDetail != null && !addressDetail.isEmpty();
    }


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", recipientName='" + recipientName + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", ward='" + ward + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", defaultAddress=" + defaultAddress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}