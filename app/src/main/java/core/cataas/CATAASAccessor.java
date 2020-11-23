package core.cataas;

import android.net.Uri;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.net.MalformedURLException;
import java.net.URL;

@Builder(builderMethodName = "requestBuilder")
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Accessors(fluent = true, prefix = "_")
public class CATAASAccessor {
    static Uri CATAAS_SAYS_BASE_URL = Uri.parse("https://cataas.com/cat/says");

    String _text;
    CatType _type;
    int _textSize;


    @SneakyThrows(MalformedURLException.class)
    public URL toUrl() {
        return new URL(
                CATAAS_SAYS_BASE_URL.buildUpon()
                        .appendPath(_text)
                        .appendQueryParameter("type", _type.value())
                        .appendQueryParameter("size", String.valueOf(_textSize))
                        .toString()
        );
    }
}
