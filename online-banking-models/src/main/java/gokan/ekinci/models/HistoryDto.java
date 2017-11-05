package gokan.ekinci.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HistoryDto {

    private String id;
    private Operation operation;
    @JsonFormat(pattern = "dd::MM::yyyy")
    private LocalDate date;

}
