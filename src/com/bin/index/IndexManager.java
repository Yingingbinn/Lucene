package com.bin.index;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class IndexManager {
    @Test
	public void IndexCreate() throws Exception{
		List<Document> docLsit = new ArrayList<Document>();
		//指定目录文件
		File dir= new File("D:\\demo");
		//循环遍历从文件夹从 取出 文件
		for(File file :dir.listFiles()){
			//获取文件夹中单个文件的名称
			String fileName =file.getName();
			//获取文件的内容
			String fileContent =FileUtils.readFileToString(file);
			//获取文件的大小
			Long fileSize = FileUtils.sizeOf(file);
			//创建文本对象
			Document docment =new Document();
			
			//获取域
			//参数一：域值
			//参数二：域名
			//参数三：是否存储
			TextField nameFile =new TextField("fileName", fileName, Store.YES);
			TextField Contentfile =new TextField("fileContent", fileContent, Store.YES);
			TextField sizeFile =new TextField("fileSize", fileSize.toString(), Store.YES);
			 
			//将所有域放入文档中
			docment.add(nameFile);
			docment.add(Contentfile);
			docment.add(sizeFile);
			
			//将所有文档放到文档集合中
			docLsit.add(docment);
		}
		//创建分词器   StandardAnalyzer是个标准分词器
		Analyzer analyzer =new StandardAnalyzer();
		//参数一  指定文档和索引存储的目录 ：内存 ，硬盘
		Directory directory =FSDirectory.open(Paths.get("D:\\dic"));
		//参数二  创建写对象的初始化对象
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
				
		//创建文档写对象
		IndexWriter indexWriter = new IndexWriter(directory, config);
		
		//将文档和索引添加到文档的写对象中
		for (Document doc:docLsit){
			indexWriter.addDocument(doc);
		}
		//提交
		indexWriter.commit();
		//关闭
		indexWriter.close();
	}
}
