package tables;

import java.util.List;
import main.AttributeType;
import main.Pair;

public interface Table {
    List<Pair<AttributeType, String>> getAttributes();
    List<String> primaryKey();
}