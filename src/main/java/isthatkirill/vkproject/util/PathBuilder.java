package isthatkirill.vkproject.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @author Kirill Emelyanov
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PathBuilder {

    public static String buildURI(HttpServletRequest httpServletRequest, Map<String, String> queryParams) {
        return UriComponentsBuilder.fromPath(httpServletRequest.getRequestURI().substring(4))
                .queryParams(toMultiValueMap(queryParams))
                .build()
                .toString();
    }

    public static String buildURI(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().substring(4);
    }

    private static MultiValueMap<String, String> toMultiValueMap(Map<String, String> queryParams) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(queryParams);
        return multiValueMap;
    }

}
