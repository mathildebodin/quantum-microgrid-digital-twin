package com.quantummicrogrid.dto.response;

public record GridResponseDTO(
        Long id,
        Double importPriceKwh,
        Double exportPriceKwh,
        Double maxImportKw,
        Double maxExportKw
) {}