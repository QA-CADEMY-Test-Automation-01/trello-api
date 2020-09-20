package trello.api.cucumber.util;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trello.api.cucumber.Helper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replacer {

    private static Pattern PATTERN = Pattern.compile("(\\[.*\\])");
    private static Logger LOGGER = LogManager.getLogger();
    private Helper helper;
    private Map<String, Object> map;

    public Replacer(Helper helper) {
        this.helper = helper;
        map = new HashMap<>();
        map.put("Board", this.helper.board);
        map.put("List", this.helper.list);

    }

    public String replaceParams(String value) {
        int lastIndex = 0;
        StringBuilder result = new StringBuilder();
        Matcher matcher = PATTERN.matcher(value);
        while (matcher.find()) {
            result.append(value, lastIndex, matcher.start())
                    .append(convert(matcher.group(1)));
            lastIndex = matcher.end();
        }
        if (lastIndex < value.length()) {
            result.append(value, lastIndex, value.length());
        }

        return result.toString();
    }

    private String convert(String match) {
        String result = match;
        String[] parts = cleanMatch(match);
        if (parts.length == 2) {
            result = getAttributeByName(parts);
        }
        return result;
    }

    private String[] cleanMatch(String match) {
        return match
            .replace("[", "")
            .replace("]", "")
            .split("\\.");
    }

    private String getAttributeByName(String[] parts) {
        String result = StringUtils.EMPTY;
        try {
            Class<?> cls = map.get(parts[0]).getClass();
            Field field = cls.getDeclaredField(parts[1]);
            field.setAccessible(true);
            result = (String) field.get(map.get(parts[0]));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.debug(e.getMessage());
        }
        return result;
    }

    public Map<String, String> replaceInMap(Map<String, String> bodyMap) {
        Map<String, String> resultMap = new HashMap<>();
        for(Map.Entry<String, String> entry: bodyMap.entrySet()) {
            resultMap.put(entry.getKey(), replaceParams(entry.getValue()));
        }
        return resultMap;
    }
}
