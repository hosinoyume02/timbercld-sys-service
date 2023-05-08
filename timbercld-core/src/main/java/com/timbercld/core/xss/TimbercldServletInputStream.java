package com.timbercld.core.xss;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class TimbercldServletInputStream  extends ServletInputStream {
    private InputStream inputStream;

    private String json;
    public TimbercldServletInputStream(String json) throws IOException {
        this.json = json;
        inputStream = null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }

    @Override
    public int read() throws IOException {
        if (null == inputStream) {
            inputStream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));
        }
        return  inputStream.read();
    }
}
