package com.jaya.cmt;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Plagiarism checker using Lucene for in-memory document indexing and searching.
 * This implementation checks text against a local index of documents.
 */
public class PlagiarismChecker {
    
    private static final Logger LOGGER = Logger.getLogger(PlagiarismChecker.class.getName());
    private final RAMDirectory index = new RAMDirectory();
    private final IndexWriterConfig config = new IndexWriterConfig(new SimpleAnalyzer());

    /**
     * Adds a document to the plagiarism check index.
     * @param content The document content to index
     * @throws IOException if indexing fails
     */
    public void addDocument(String content) throws IOException {
        if (content == null || content.trim().isEmpty()) {
            LOGGER.warning("Attempting to index empty content");
            return;
        }
        
        try (IndexWriter writer = new IndexWriter(index, config)) {
            Document doc = new Document();
            doc.add(new TextField("content", content, Field.Store.YES));
            writer.addDocument(doc);
        } catch (IOException e) {
            LOGGER.severe("Failed to add document to plagiarism index: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Checks if the given text is plagiarized against indexed documents.
     * @param queryText The text to check
     * @return true if plagiarism is detected, false otherwise
     * @throws Exception if search fails
     */
    public boolean isPlagiarized(String queryText) throws Exception {
        if (queryText == null || queryText.trim().isEmpty()) {
            LOGGER.warning("Attempting to check empty text");
            return false;
        }
        
        try {
            IndexSearcher searcher = new IndexSearcher(
                org.apache.lucene.index.DirectoryReader.open(index)
            );
            QueryParser parser = new QueryParser("content", new SimpleAnalyzer());
            Query query = parser.parse(QueryParser.escape(queryText));

            TopDocs results = searcher.search(query, 1);
            return results.totalHits.value > 0;
        } catch (Exception e) {
            LOGGER.severe("Plagiarism check failed: " + e.getMessage());
            throw e;
        }
    }
}
