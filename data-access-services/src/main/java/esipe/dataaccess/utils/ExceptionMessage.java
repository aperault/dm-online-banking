package esipe.dataaccess.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionMessage {

    private String message;
    private String className;
    private String path;
    private String date;
}
