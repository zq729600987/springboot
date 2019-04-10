package com.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.zip.CRC32;


public class DynamicBean extends HashMap<String, Object> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public String getString(String key) {
        Object val = (Object) this.get(key);
        if( val==null )
            return null;
        if (val instanceof BigDecimal)
            return ((BigDecimal) val).toString();
        if (val instanceof Integer)
            return ((Integer) val).toString();
        if (val instanceof Long)
            return ((Long) val).toString();
        if (val instanceof Double)
            return ((Double) val).toString();
        return  String.valueOf(val);
    }

    public Integer getInteger(String key) {
        Object val = this.get(key);
        if (val == null)
            return null;
        if (val instanceof Integer)
            return (Integer) val;
        if (val instanceof Long)
            return ((Long) val).intValue();
        else if (val instanceof BigDecimal)
            return ((BigDecimal) val).intValue();
        else if (val instanceof BigInteger)
            return ((BigInteger) val).intValue();
        else if (val instanceof String) {
            String str = (String) val;
            if (StringUtils.isNumeric(str)) {
                int dotPos = str.indexOf(".");
                if (dotPos >= 0)
                    str = str.substring(0, dotPos);
                return Integer.parseInt(str);
            }
            return null;
        }
        return (Integer)val;
    }

    public Long getLong(String key) {
        Object val = this.get(key);
        if (val == null)
            return null;
        if (val instanceof Long)
            return (Long) val;
        else if (val instanceof Integer)
            return Long.parseLong(val.toString());
        else if (val instanceof BigDecimal)
            return ((BigDecimal) val).longValue();
        else if (val instanceof BigInteger)
            return ((BigInteger) val).longValue();
        else if (val instanceof String) {
            String str = (String) val;
            if (StringUtils.isNumeric(str)) {
                int dotPos = str.indexOf(".");
                if (dotPos >= 0)
                    str = str.substring(0, dotPos);
                return Long.parseLong(str);
            }
        }
        return (Long)val;
    }

    public Float getFloat(String key) {
        Object val = this.get(key);
        if (val == null)
            return null;
        if (val instanceof Long)
            return (Float) val;
        else if (val instanceof BigDecimal)
            return ((BigDecimal) val).floatValue();
        else if (val instanceof String) {
            String str = (String) val;
            if (StringUtils.isNumeric(str)) {
                return Float.parseFloat(str);
            }
        }
        return null;
    }

    public Double getDouble(String key) {
        Object val = this.get(key);
        if (val == null)
            return null;
        if (val instanceof Double)
            return (Double) val;
        else if (val instanceof BigDecimal)
            return ((BigDecimal) val).doubleValue();
        else if (val instanceof String) {
            String str = (String) val;
            if (StringUtils.isNumeric(str)) {
                return Double.parseDouble(str);
            }
        }else{
            String strval = val.toString();
            if( isDecimal ( strval ) ){
                return Double.parseDouble(strval);
            }
            return null;
        }
        return null;
    }

    public static boolean isDecimal(String str) {
        if (str == null || "".equals(str))
            return false;
        java.util.regex.Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        return pattern.matcher(str).matches();
    }

    public Object put(String key, Object value) {
        if (value instanceof java.sql.Blob) {
            Blob blob = (Blob) value;
            InputStream is = null;
            byte[] b = null;
            try {
                is = blob.getBinaryStream();
                b = new byte[(int) blob.length()];
                is.read(b);
                value = b;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (value instanceof java.sql.Clob) {
            Clob clob = (Clob) value;
            StringBuffer sbResult = new StringBuffer();
            Reader isClob;
            String contentstr = "";
            BufferedReader bfClob=null;
            try {
                isClob = clob.getCharacterStream();
                bfClob = new BufferedReader(isClob);
                String strClob = bfClob.readLine();
                while (strClob != null) {
                    sbResult.append(strClob);
                    strClob = bfClob.readLine();
                }
                value = sbResult.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    if( bfClob!=null )
                        bfClob.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.put(key, value);
    }

    public static String getCRC32(String str) {
        CRC32 crc = new CRC32();
        crc.update(str.getBytes());
        long val =  crc.getValue();
        return Long.toHexString(val);
    }
}

