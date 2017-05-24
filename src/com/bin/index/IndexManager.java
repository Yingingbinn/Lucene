package com.bin.index;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;

public class IndexManager {
 
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
	}
}
