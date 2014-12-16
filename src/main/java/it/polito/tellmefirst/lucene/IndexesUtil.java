/*-
 * Copyright (C) 2012 Federico Cairo, Giuseppe Futia, Federico Benedetto
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.polito.tellmefirst.lucene;

import it.polito.tellmefirst.util.TMFVariables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.document.Document;

public class IndexesUtil {

	static Log LOG = LogFactory.getLog(IndexesUtil.class);
	public static SimpleSearcher ITALIAN_CORPUS_INDEX_SEARCHER;
	public static SimpleSearcher ENGLISH_CORPUS_INDEX_SEARCHER;

	public static void init() throws IOException {
		LOG.debug("[initializator] - BEGIN");

		// build italian searcher
		Directory contextIndexDirIT = LuceneManager.pickDirectory(new File(TMFVariables.CORPUS_INDEX_IT));
		LOG.info("Corpus index used for italian: " + contextIndexDirIT);
		LuceneManager contextLuceneManagerIT = new LuceneManager(contextIndexDirIT);
		contextLuceneManagerIT.setLuceneDefaultAnalyzer(new ItalianAnalyzer(Version.LUCENE_36, TMFVariables.STOPWORDS_IT));
		ITALIAN_CORPUS_INDEX_SEARCHER = new SimpleSearcher(contextLuceneManagerIT);

		// build english searcher
		Directory contextIndexDirEN = LuceneManager.pickDirectory(new File(TMFVariables.CORPUS_INDEX_EN));
		LOG.info("Corpus index used for english: " + contextIndexDirEN);
		LuceneManager contextLuceneManagerEN = new LuceneManager(contextIndexDirEN);
		contextLuceneManagerEN.setLuceneDefaultAnalyzer(new EnglishAnalyzer(Version.LUCENE_36, TMFVariables.STOPWORDS_EN));
		ENGLISH_CORPUS_INDEX_SEARCHER = new SimpleSearcher(contextLuceneManagerEN);

		LOG.debug("[initializator] - END");
	}

}
