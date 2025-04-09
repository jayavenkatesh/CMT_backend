package com.jaya.cmt;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

public class PlagiarismChecker {
    private RAMDirectory index = new RAMDirectory();
    private IndexWriterConfig config = new IndexWriterConfig(new SimpleAnalyzer());

    public void addDocument(String content) throws IOException {
        IndexWriter writer = new IndexWriter(index, config);
        Document doc = new Document();
        doc.add(new org.apache.lucene.document.TextField("content", content, org.apache.lucene.document.Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    public boolean isPlagiarized(String queryText) throws Exception {
        IndexSearcher searcher = new IndexSearcher(org.apache.lucene.index.DirectoryReader.open(index));
        QueryParser parser = new QueryParser("content", new SimpleAnalyzer());
        Query query = parser.parse(QueryParser.escape(queryText));

        TopDocs results = searcher.search(query, 1);
        return results.totalHits.value > 0; // If a match is found, it's plagiarized
    }
}
