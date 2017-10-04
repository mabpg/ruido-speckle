package py.com.mabpg.ruidospeckle.models;

/**
 *
 * @author Derlis Argüello
 */
public class Constant {
    public static final int CHANNEL_RED = 1;
    public static final int CHANNEL_GREEN = 2;
    public static final int CHANNEL_BLUE = 3;
    public static final int MAX_INTENSITY_VALUE = 255;
    /*
        --Realizar esta consulta para saber el catalogo y schema
        SELECT *
        FROM information_schema.columns
        WHERE table_schema = 'your_schema'
        AND table_name   = 'your_table'
        link: http://dba.stackexchange.com/questions/22362/how-do-i-list-all-columns-for-a-specified-table
    */
    public static final String CATALOG = "tesisadrimab";
    public static final String SCHEMA = "public";
}
