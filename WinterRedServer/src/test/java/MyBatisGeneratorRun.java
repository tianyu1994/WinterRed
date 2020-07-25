import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class MyBatisGeneratorRun {

    //数据库类型(需要根据项目修改)
    private final DbType dbType = DbType.MYSQL;
    //数据库连结信息(需要根据项目修改)
    private final String dbUrl = "jdbc:mysql://127.0.0.1:3306/winter_red?useUnicode=true&characterEncoding=utf8";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String userName = "root";
    private final String password = "root";

    //项目名(需要根据项目修改)
    private final String projectName = "WinterRedServer";
    //指定包名(需要根据项目修改)
    private final String packageName = "org.codeforworld.winterredserver";
    //controller基础类
    private final String superControllerClass = packageName + ".controller";
    //entity基础类
    private final String superEntityClass = packageName + ".entity";
    //模块名 如果有模块名，则需在模块名前加. 例：.log(需要根据项目修改)
    private final String moduleName = "";
    //作者名
    private final String author = "kfzx-ganhy";
    //指定生成的表名(需要根据项目修改)
    private final String[] tableNames = new String[]{"table_name"};


    @Test
    public void generateCode() {
        //serviceNameStartWithI：user -> UserService, 设置成true: user -> IUserService
//        generateByTables(false, packageName, tableNames);
    }
    /**
     * 根据表自动生成
     * @param serviceNameStartWithI 默认为false
     * @param packageName      包名
     * @param tableNames      表名
     * @author Terry
     */
    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(serviceNameStartWithI);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);
        //自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig);
    }
    /**
     * 集成
     * @param dataSourceConfig 配置数据源
     * @param strategyConfig  策略配置
     * @param config      全局变量配置
     * @param packageConfig  包名配置
     * @author Terry
     */
    private void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig config, PackageConfig packageConfig) {
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
    /**
     * 设置包名
     * @param packageName 父路径包名
     * @param packageName 模块名
     * @return PackageConfig 包名配置
     * @author Terry
     */
    private PackageConfig getPackageConfig(String packageName) {
        if(!StringUtils.isEmpty(moduleName)){
            return new PackageConfig()
                    .setParent(packageName)
                    .setXml("mapper.xml" + moduleName)
                    .setMapper("mapper." + moduleName)
                    .setController("controller." + moduleName)
                    .setEntity("entity." + moduleName);
        }else{
            return new PackageConfig()
                    .setParent(packageName)
                    .setXml("mapper.xml")
                    .setMapper("mapper")
                    .setController("controller")
                    .setEntity("entity");
        }
    }
    /**
     * 全局配置
     * @param serviceNameStartWithI false
     * @return GlobalConfig
     * @author Terry
     */
    private GlobalConfig getGlobalConfig(boolean serviceNameStartWithI) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setActiveRecord(false)
                //作者
                .setAuthor(author)
                //设置输出路径
                .setOutputDir(getOutputDir(projectName))
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            //设置service名
            globalConfig.setServiceName("%sService");
        }
        return globalConfig;
    }
    /**
     * 返回项目路径(需要根据项目修改)
     * @param projectName 项目名
     * @return 项目路径
     * @author Terry
     */
    private String getOutputDir(String projectName) {
//        String path = this.getClass().getClassLoader().getResource("").getPath();
//        int index = path.indexOf(projectName);
        return "C:/Users/ImSherlocked/Desktop/winterRed/WinterRed/" + projectName + "/src/main/java/";
    }
    /**
     * 策略配置(需要根据项目修改)
     * @param tableNames 表名
     * @return StrategyConfig
     * @author Terry
     */
    private StrategyConfig getStrategyConfig(String... tableNames) {
        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
                .setCapitalMode(true)
                //从数据库表到文件的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //需要生成的的表名，多个表名传数组
                .setInclude(tableNames)
                //公共父类
//                .setSuperControllerClass(superControllerClass)
//                .setSuperEntityClass(superEntityClass)
                // 写于父类中的公共字段
//                .setSuperEntityColumns("id")
                //使用lombok
                .setEntityLombokModel(true)
                //rest风格
                .setRestControllerStyle(true);
    }
    /**
     * 配置数据源
     * @return 数据源配置 DataSourceConfig
     * @author Terry
     */
    private DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig().setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(userName)
                .setPassword(password)
                .setDriverName(driver);
    }
    /**
     * 根据表自动生成
     * @param packageName 包名
     * @param tableNames 表名
     * @author Terry
     */
    @SuppressWarnings("unused")
    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}
