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
		//ָ��Ŀ¼�ļ�
		File dir= new File("D:\\demo");
		//ѭ���������ļ��д� ȡ�� �ļ�
		for(File file :dir.listFiles()){
			//��ȡ�ļ����е����ļ�������
			String fileName =file.getName();
			//��ȡ�ļ�������
			String fileContent =FileUtils.readFileToString(file);
			//��ȡ�ļ��Ĵ�С
			Long fileSize = FileUtils.sizeOf(file);
			//�����ı�����
			Document docment =new Document();
			
			//��ȡ��
			//����һ����ֵ
			//������������
			//���������Ƿ�洢
			TextField nameFile =new TextField("fileName", fileName, Store.YES);
			TextField Contentfile =new TextField("fileContent", fileContent, Store.YES);
			TextField sizeFile =new TextField("fileSize", fileSize.toString(), Store.YES);
			 
			//������������ĵ���
			docment.add(nameFile);
			docment.add(Contentfile);
			docment.add(sizeFile);
			
			//�������ĵ��ŵ��ĵ�������
			docLsit.add(docment);
		}
		//�����ִ���   StandardAnalyzer�Ǹ���׼�ִ���
		Analyzer analyzer =new StandardAnalyzer();
		//����һ  ָ���ĵ��������洢��Ŀ¼ ���ڴ� ��Ӳ��
		Directory directory =FSDirectory.open(Paths.get("D:\\dic"));
		//������  ����д����ĳ�ʼ������
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
				
		//�����ĵ�д����
		IndexWriter indexWriter = new IndexWriter(directory, config);
		
		//���ĵ���������ӵ��ĵ���д������
		for (Document doc:docLsit){
			indexWriter.addDocument(doc);
		}
		//�ύ
		indexWriter.commit();
		//�ر�
		indexWriter.close();
	}
}
