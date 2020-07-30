package org.codeforworld.winterredserver.utils;

import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import org.codeforworld.winterredserver.service.impl.ProfessionalFieldServiceImpl;

import java.io.File;
import java.io.IOException;

//@author hankcs
//Github：https://github.com/kervin521/HanLP
public class Comparator {
    //两个文本关键词的余弦相似度阈值0.5
    private static final double SIMILARITY_THRESHOLD_VALUE_INSERT = 0.55F;
    private static final double SIMILARITY_THRESHOLD_VALUE_RECOMMEND = 0.45F;

    private static final String TRAIN_FILE_NAME = "./src/main/resources/hankstext/corpus-similarity/搜狗文本分类语料库已分词.txt";
    private static final String MODEL_FILE_NAME = "./src/main/resources/data/classification/model/word2vec.txt";
    /**
     * 计算两个文本相似度
     * @param text1 文本1
     * @param text2 文本2
     * @return 是否相似
     */
    public static boolean compareTwoTextSimilarity(String text1, String text2, boolean isInsertDataBase) throws IOException {
        WordVectorModel wordVectorModel = trainOrLoadModel();
        DocVectorModel docVectorModel = new DocVectorModel(wordVectorModel);
        float degree = docVectorModel.similarity(text1, text2);
        System.out.println("文本1：" + text1 + "。 文本2：" + text2 + "。 相似度：" + degree);
        if (isInsertDataBase) {
            return degree >= SIMILARITY_THRESHOLD_VALUE_INSERT;
        }
        return degree >= SIMILARITY_THRESHOLD_VALUE_RECOMMEND;
    }

    private static boolean isFileExisted(String path) {
        File file = new File(path);
        return file.isFile() && file.exists() && file.length() != 0;
    }

    static WordVectorModel trainOrLoadModel() throws IOException
    {
        if (!isFileExisted(MODEL_FILE_NAME))
        {
            if (!IOUtil.isFileExisted(TRAIN_FILE_NAME))
            {
                System.err.println("语料不存在，请阅读文档了解语料获取与格式：https://github.com/hankcs/HanLP/wiki/word2vec");
                System.exit(1);
            }
            Word2VecTrainer trainerBuilder = new Word2VecTrainer();
            return trainerBuilder.train(TRAIN_FILE_NAME, MODEL_FILE_NAME);
        }

        return loadModel();
    }

    static WordVectorModel loadModel() throws IOException
    {
        return new WordVectorModel(MODEL_FILE_NAME);
    }
}
