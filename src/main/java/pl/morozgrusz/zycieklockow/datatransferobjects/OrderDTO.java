package pl.morozgrusz.zycieklockow.datatransferobjects;

public class OrderDTO
{
    private int deliveryMethodId;
    private int addressId;

    public int getAddressId()
    {
        return addressId;
    }

    public void setAddressId(int addressId)
    {
        this.addressId = addressId;
    }

    public int getDeliveryMethodId()
    {
        return deliveryMethodId;
    }

    public void setDeliveryMethodId(int deliveryMethodId)
    {
        this.deliveryMethodId = deliveryMethodId;
    }
}
