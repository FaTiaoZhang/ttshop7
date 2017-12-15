package com.dhc.ttshop.lucene.index;

import com.dhc.ttshop.lucene.dao.IBookDao;
import com.dhc.ttshop.lucene.dao.impl.BookDaoImpl;
import com.dhc.ttshop.lucene.pojo.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/5
 * Time: 9:55
 * Version:V1.0
 */
public class IndexManager {

    //创建索引库
    @Test
    public void createIndex() throws Exception{
        //1 采集数据
        IBookDao bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.listBooks();
        //2 创建文档域
        List<Document> documentList = new ArrayList<Document>();
        //3 遍历bookList
        for (Book book : bookList){
            //每遍历一次book的一条记录产生一个文档对象
            Document document = new Document();
            //图书ID:不分词 要索引 要存储
            Field idField = new StringField("id", book.getId() + "", Field.Store.YES);
            //图书名称：分词、索引、存储
            Field nameField = new TextField("name",book.getName(), Field.Store.YES);
            //图书价格：不分词、索引、存储
            Field priceField = new FloatField("price",book.getPrice(),Field.Store.YES);
            //图片地址：不分词、不索引、存储
            Field picField = new StoredField("pic",book.getPic());
            //图片描述：分词、索引、不存储
            Field descriptionField = new TextField("description",book.getDescription(),Field.Store.NO);
            //将每个field添加到文档中
            document.add(idField);
            document.add(nameField);
            document.add(priceField);
            document.add(picField);
            document.add(descriptionField);
            documentList.add(document);
        }

        //创建默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        //IndexWriterConfig (Lucene的版本号)
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //directory
        Directory directory = FSDirectory.open(new File("d:/bookindex"));
        //IndexWriter
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //for循环写入
        for (Document document : documentList){
            indexWriter.addDocument(document);
        }
        //writer关闭
        indexWriter.close();
    }

    //查询索引
    @Test
    public void searchIndex() throws Exception {
        QueryParser parser = new QueryParser("name",new StandardAnalyzer());
        Query query = parser.parse("name:java");
        //指定索引库的地址
        File indexFile = new File("d:/bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        //搜索索引库的内容
        TopDocs topDocs = searcher.search(query, 10);
        System.out.println("匹配的记录总数为：" + topDocs.totalHits);
    }


    //删除指定索引或者删除所有索引
    @Test
    public void deleteIndex() throws Exception{
        //创建默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        //指定索引库的地址
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        Directory directory = FSDirectory.open(new File("d:/bookindex"));
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //删除所有
        indexWriter.deleteAll();
//        Term term = new Term("name", "solr");
//        indexWriter.deleteDocuments(term);
        indexWriter.close();
    }


    @Test
    public void updateIndex() throws Exception{
        //创建默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        //指定索引库的地址
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        Directory directory = FSDirectory.open(new File("d:/bookindex"));
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //添加document
        Term term = new Term("name", "solr"); // name=solr
        Document document = new Document();
        document.add(new TextField("name","java",Field.Store.YES));
        //选择更新操作
        //若存在的话，就是更新，若不存在的话就是新增
        indexWriter.updateDocument(term, document);//name=java
        indexWriter.close();
    }

}
