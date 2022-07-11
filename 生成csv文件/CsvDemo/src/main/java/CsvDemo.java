import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yuanqizhang
 */
public class CsvDemo {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\test\\demo.csv";
        File csvFile = new File(filePath);
        FileUtil.mkdir(csvFile.getParentFile());

        // 写入 BOM 文件头
        byte[] uft8bom = {(byte) 0xef, (byte) 0xbb, (byte) 0xbf};
        FileOutputStream fos = new FileOutputStream(csvFile, true);
        fos.write(uft8bom);

        // isAppend 参数必须为 true，不然会覆盖掉前面的  BOM 文件头
        CsvWriter csv = CsvUtil.getWriter(csvFile, CharsetUtil.CHARSET_UTF_8, true);

        csv.write(
                new String[] {"头一", "头二", "头三"},
                new String[] {"a2", "b2", "c2"},
                new String[] {"a3", "b3", "c3"}
        );
    }
}
