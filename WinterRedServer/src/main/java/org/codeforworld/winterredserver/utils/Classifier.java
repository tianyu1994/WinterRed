package org.codeforworld.winterredserver.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.codeforworld.winterredserver.WinterRedServerStartUp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Classifier {
    /**
     * 搜狗文本分类语料库5个类目，每个类目下1000篇文章，共计5000篇文章
     */
    public static final String CORPUS_FOLDER = "./src/main/resources/hankstext/训练集";
    /**
     * 模型保存路径
     */
    public static final String MODEL_PATH = "./src/main/resources/data/classification/model/classification-model.ser";
    private static final String COUNTRY_PATH = "./src/main/resources/country.txt";

    public static String recognizeTopicOf(String msg) throws IOException {
        IClassifier classifier = new NaiveBayesClassifier(WinterRedServerStartUp.model);
        return classifier.classify(msg);
    }

    public static String recognizeLocationOf(String msg) throws IOException {
        File file = new File(COUNTRY_PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bf = new BufferedReader(new InputStreamReader(fileInputStream));
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        List<Term> termList = segment.seg(msg);
        List<String> placeName = new ArrayList<>();
        for (Term term : termList) {
            if (term.nature.equals(Nature.ns)) {
                placeName.add(term.word);
            }
        }
        String country = null;
        for (String ns : placeName) {
            while ((country = bf.readLine()) != null) {
                if (country.equals(ns)) {
                    return country;
                }
            }
        }
        //todo 国家地区解析
        return "";
    }

    public static NaiveBayesModel trainOrLoadModel() throws IOException
    {
        System.out.println("加载模型ing");
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_PATH);
        if (model != null) return model;

        System.out.println("重新训练了");
        File corpusFolder = new File(CORPUS_FOLDER);
        if (!corpusFolder.exists() || !corpusFolder.isDirectory())
        {
            System.err.println("没有文本分类语料，请阅读IClassifier.train(java.lang.String)中定义的语料格式与语料下载：" +
                    "https://github.com/hankcs/HanLP/wiki/%E6%96%87%E6%9C%AC%E5%88%86%E7%B1%BB%E4%B8%8E%E6%83%85%E6%84%9F%E5%88%86%E6%9E%90");
            System.exit(1);
        }

        IClassifier classifier = new NaiveBayesClassifier(); // 创建分类器，更高级的功能请参考IClassifier的接口定义
        classifier.train(CORPUS_FOLDER);                     // 训练后的模型支持持久化，下次就不必训练了
        model = (NaiveBayesModel) classifier.getModel();
        IOUtil.saveObjectTo(model, MODEL_PATH);
        return model;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(recognizeLocationOf("猫科"));
    }
}
