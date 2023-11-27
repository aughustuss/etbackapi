package et.backapi.adapter.dto;

public record CreateAddressDTO (
        String addressStreet,
        String addressAddress,
        Integer addressNumber,
        Number addressZipCode,
        String addressNeighborhood
) {
}
