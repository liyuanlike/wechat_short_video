package com.yuhangTao.mybatis.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
* 注意路径问题还是需要修改，绝对路径是不负责任的！！！
*
* */


public class GeneratorDisplay {

    public void generator() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        boolean overwrite=true;
        String path=GeneratorDisplay.class.getClassLoader()
                .getResource("generator/generatorConfig.xml").getPath();
        //指定逆向工程配置文件
        File configfile=new File(path);
        ConfigurationParser cp=new ConfigurationParser(warnings);
        Configuration config=cp.parseConfiguration(configfile);
        DefaultShellCallback callback=new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args){
        GeneratorDisplay generatorDisplay=new GeneratorDisplay();
        try {
            generatorDisplay.generator();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
