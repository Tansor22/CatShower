package core.services;

import android.content.Context;
import core.utils.Utils;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ModernPhrasesSupplier implements Supplier<String> {
    static String PROPERTIES_FILE_NAME = "application.properties";
    List<String> NOUNS;
    List<String> ADJECTIVES;
    List<String> ADVERBS;
    List<String> VERBS;


    @SneakyThrows(IOException.class)
    public ModernPhrasesSupplier(Context context) {
        Properties appProps = new Properties();
        appProps.load(context.getAssets().open(PROPERTIES_FILE_NAME));
        NOUNS = new ArrayList<>(Arrays.asList(appProps.getProperty("modern.nouns", "").split(",")));
        ADJECTIVES = new ArrayList<>(Arrays.asList(appProps.getProperty("modern.adjectives", "").split(",")));
        ADVERBS = new ArrayList<>(Arrays.asList(appProps.getProperty("modern.adverbs", "").split(",")));
        VERBS = new ArrayList<>(Arrays.asList(appProps.getProperty("modern.verbs", "").split(",")));
    }

    public String getModernPhrase() {
        List<String> terms = Utils.getRandomElements(NOUNS, 2);
        return StringUtils.capitalize(String.join(" \n", Utils.getRandomElement(ADJECTIVES), terms.get(0),
                Utils.getRandomElement(ADVERBS), Utils.getRandomElement(VERBS), terms.get(1)));
    }

    @Override
    public String get() {
        return getModernPhrase();
    }
}
