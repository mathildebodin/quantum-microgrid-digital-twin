package com.quantummicrogrid.dto.request;


import jakarta.validation.constraints.*;

public record GridRequestDTO(
        @NotNull @Positive Double importPriceKwh,
        @NotNull @Positive Double exportPriceKwh,
        @NotNull @Positive Double maxImportKw,
        @NotNull @Positive Double maxExportKw
) {}