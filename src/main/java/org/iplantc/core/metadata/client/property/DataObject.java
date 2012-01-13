package org.iplantc.core.metadata.client.property;

import org.iplantc.core.jsonutil.JsonUtil;
import org.iplantc.core.metadata.client.JSONMetaDataObject;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * 
 * A model that represent DataObjects
 * 
 * @author sriram
 * 
 */
public class DataObject extends JSONMetaDataObject {
    public static String INPUT_TYPE = "Input"; //$NON-NLS-1$
    public static String OUTPUT_TYPE = "Output"; //$NON-NLS-1$

    public static String MULTIPLICITY = "multiplicity"; //$NON-NLS-1$
    public static String ORDER = "order"; //$NON-NLS-1$
    public static String CMD_SWITCH = "cmdSwitch"; //$NON-NLS-1$
    public static String FILE_INFO_TYPE_ID = "file_info_type_id"; //$NON-NLS-1$
    public static String FILE_INFO_TYPE = "file_info_type"; //$NON-NLS-1$
    public static String OUTPUT_FILENAME = "output_filename"; //$NON-NLS-1$
    public static String FORMAT_ID = "format_id"; //$NON-NLS-1$
    public static String FORMAT = "format"; //$NON-NLS-1$

    /** whether a filename is required (input fields only) */
    public static String REQUIRED = "required"; //$NON-NLS-1$

    public static String MULTIPLICITY_ONE = "One"; //$NON-NLS-1$
    public static String MULTIPLICITY_MANY = "Many"; //$NON-NLS-1$
    public static String MULTIPLICITY_FOLDER = "folder"; //$NON-NLS-1$
    
    public static String RETAIN = "retain"; //$NON-NLS-1$

    private int order;
    private String multiplicity;
    private String cmdSwitch;
    private String fileTypeId;
    private String fileType;
    private String filename;
    private String formatId;
    private String format;
    private boolean required;
    private boolean retain;

    /**
     * Create a new instance of DataObject
     * 
     * @param json
     */
    public DataObject(JSONObject json) {
        super(json);
    }

    /**
     * Create a new instance of DataObject
     * 
     */
    public DataObject() {
        super("{}"); //$NON-NLS-1$
    }

    @Override
    protected void parseData(JSONObject json) {
        super.parseData(json);

        String multiplicity = JsonUtil.getString(json, MULTIPLICITY);
        if (multiplicity != null && !multiplicity.isEmpty()) {
            setMultiplicity(multiplicity);
        } else {
            setMultiplicity(MULTIPLICITY_ONE);
        }

        JSONValue order = json.get(ORDER);
        if (order != null && order.isNumber() != null) {
            setOrder((int)order.isNumber().doubleValue());
        } else {
            setOrder(-1);
        }

        setCmdSwitch(JsonUtil.getString(json, CMD_SWITCH));
        setFileTypeId(JsonUtil.getString(json, FILE_INFO_TYPE_ID));
        setFileType(JsonUtil.getString(json, FILE_INFO_TYPE));
        setOutputFilename(JsonUtil.getString(json, OUTPUT_FILENAME));

        setFormatId(getFormats(JsonUtil.getArray(json, FORMAT_ID)));
        setFormat(getFormats(JsonUtil.getArray(json, FORMAT)));

        setRequired(JsonUtil.getBoolean(json, REQUIRED, true));
        setRetain(JsonUtil.getBoolean(json, RETAIN, OUTPUT_TYPE.equals(getType())));
    }

    private String getFormats(JSONArray arr) {
        StringBuffer formats = new StringBuffer(""); //$NON-NLS-1$
        if (arr != null) {
            for (int i = 0; i < arr.size(); i++) {
                formats.append(JsonUtil.trim(arr.get(i).toString()) + "|"); //$NON-NLS-1$
            }

            if (formats.length() > 0 && formats.charAt(formats.length() - 1) == '|') {
                formats.deleteCharAt(formats.length() - 1);
            }
        }

        return formats.toString();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(String type) {
        super.setType(type);

        setRetain(OUTPUT_TYPE.equals(getType()));
    }

    /**
     * set the multiplicity
     * 
     * @param multiplicity
     */
    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

    /**
     * set the order
     * 
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * set the command line switch
     * 
     * @param cmdSwitch
     */
    public void setCmdSwitch(String cmdSwitch) {
        this.cmdSwitch = cmdSwitch;
    }

    /**
     * set the file type id
     * 
     * @param fileTypeId
     */
    public void setFileTypeId(String fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    /**
     * set the file type
     * 
     * @param fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * set the output file name
     * 
     * @param filename
     */
    public void setOutputFilename(String filename) {
        this.filename = filename;
    }

    /**
     * set the format id
     * 
     * @param formatId
     */
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    /**
     * set the format
     * 
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * set whether the field is required in the wizard
     * 
     * @param required
     */
    public void setRequired(boolean required) {
        this.required = required;
    }
    
    /**
     * set whether the data object should be retained
     * 
     * @param retain
     */
    public void setRetain(boolean retain) {
        this.retain = retain;
    }

    /**
     * @return the retain
     */
    public boolean isRetain() {
        return retain;
    }

    /**
     * 
     * get the multiplicity
     * 
     * @return multiplicity
     */
    public String getMultiplicity() {
        return getNonNullString(multiplicity);
    }

    /**
     * get the command line order
     * 
     * @return order
     */
    public int getOrder() {
        return order;
    }

    /**
     * get the command line switch
     * 
     * @return command line switch
     */
    public String getCmdSwitch() {
        return getNonNullString(cmdSwitch);
    }

    /**
     * get the FileInfo type
     * 
     * @return FileInfo type
     */
    public String getFileInfoType() {
        return getNonNullString(fileType);
    }

    /**
     * get the output file name
     * 
     * @return output file name
     */
    public String getOutputFilename() {
        return getNonNullString(filename);
    }

    /**
     * get the format
     * 
     * @return format
     */
    public String getFormat() {
        return getNonNullString(format);
    }

    /**
     * gets if the DataObject is required
     * 
     * @return if this DataObject is required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * get File Info type id
     * 
     * @return file info type id
     */
    public String getFileInfoTypeId() {
        return getNonNullString(fileTypeId);
    }

    /**
     * 
     * get the format id
     * 
     * @return format id
     */
    public String getFormatId() {
        return getNonNullString(formatId);
    }

    private String getNonNullString(String ret) {
        if (ret == null) {
            return ""; //$NON-NLS-1$
        }

        return ret;
    }

    /**
     * Constructs a label suitable for the command line ordering preview or as a default for DataObjects
     * that don't have a label already set.
     * 
     * @return A label for this DataObject suitable as a command line ordering preview.
     */
    public String getCmdLinePreviewLabel() {
        String outputFilename = getOutputFilename();
        String name = getName();
        String type = getType();
        String cmdSwitch = getCmdSwitch();

        String label = outputFilename.isEmpty() ? name : outputFilename;

        if (!cmdSwitch.isEmpty()) {
            label = cmdSwitch.trim() + " "; //$NON-NLS-1$

            if (!outputFilename.isEmpty()) {
                label += outputFilename;
            }
        }

        if (!type.isEmpty()) {
            label += "(" + type + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        }

        return label;
    }

    /**
     * 
     * Convert this DataObject into a JSONObject
     * 
     * @return a JSONObject representation of this DataObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject ret = super.toJson();

        ret.put(MULTIPLICITY, new JSONString(getMultiplicity()));
        ret.put(CMD_SWITCH, new JSONString(getCmdSwitch()));
        ret.put(ORDER, new JSONNumber(getOrder()));
        ret.put(FILE_INFO_TYPE_ID, new JSONString(getFileInfoTypeId()));
        ret.put(FILE_INFO_TYPE, new JSONString(getFileInfoType()));
        ret.put(OUTPUT_FILENAME, new JSONString(getOutputFilename()));
        ret.put(RETAIN, JSONBoolean.getInstance(isRetain()));

        if (getFormatId() == null || getFormatId().equals("")) { //$NON-NLS-1$
            ret.put(FORMAT_ID, new JSONArray());
        } else {
            ret.put(FORMAT_ID, getFormatIdAsJson());
        }

        if (getFormat() == null || getFormat().equals("")) { //$NON-NLS-1$
            ret.put(FORMAT, new JSONArray());
        } else {
            ret.put(FORMAT, getFormatAsJson());
        }

        ret.put(REQUIRED, JSONBoolean.getInstance(isRequired()));

        return ret;
    }

    private JSONArray getFormatIdAsJson() {
        return getAsJson(getFormatId());
    }

    private JSONArray getFormatAsJson() {
        return getAsJson(getFormat());
    }

    private JSONArray getAsJson(String str) {
        JSONArray arr = new JSONArray();
        String[] tokens = str.split("\\|"); //$NON-NLS-1$

        if (tokens != null && tokens.length > 0) {
            for (int i = 0; i < tokens.length; i++) {
                arr.set(i, new JSONString(tokens[i]));
            }
        }

        return arr;
    }

}
