package ro.msg.learning.shop.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import ro.msg.learning.shop.exception.CsvCastException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.List;

public class CSVMessageConverter<T> extends AbstractGenericHttpMessageConverter {

    public CSVMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = httpOutputMessage.getBody();
        try {
            toCsv((List<T>) o, outputStream);
        } catch (ClassCastException classCastException) {
            throw new CsvCastException(classCastException.getMessage());
        }
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return fromCsv(aClass, httpInputMessage.getBody());
    }

    @Override
    public Object read(Type type, Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(aClass, httpInputMessage);
    }

    private List<T> fromCsv(Class<T> csvClass, InputStream csvInputStream) throws IOException {

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(csvClass);

        MappingIterator<T> mappingIterator = mapper.readerFor(csvClass).with(schema)
                .readValues(csvInputStream);

        return mappingIterator.readAll();
    }

    private void toCsv(List<T> pojoList, OutputStream csvOutputStream) throws IOException {

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(pojoList.get(0).getClass());
        ObjectWriter objectWriter = mapper.writer(schema.withLineSeparator("\n"));

        StringBuilder csvValue = new StringBuilder();
        for (T pojo : pojoList) {
            csvValue.append(objectWriter.writeValueAsString(pojo));
        }

        csvOutputStream.write(csvValue.toString().getBytes());
    }


}