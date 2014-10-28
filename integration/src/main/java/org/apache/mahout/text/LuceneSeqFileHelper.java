/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.mahout.text;

import com.google.common.base.Strings;
import org.apache.hadoop.io.Text;
import org.apache.lucene.document.Document;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 *
 *
 **/
class LuceneSeqFileHelper {

  public static final String SEPARATOR_FIELDS = ";";
  public static final String SEPARATOR_VALUES = ",";
  public static final int USE_TERM_INFOS = 1;

  private LuceneSeqFileHelper() {}

  public static void populateValues(final Document document, final Text theValue, final List<String> fields) {
        final StringBuilder valueBuilder = new StringBuilder();
        for (int i = 0; i < fields.size(); ++i) {
            final String field = fields.get(i);
            final String[] fieldValue = document.getValues(field);
            if (fieldValue.length != 0) {
                for (int j = 0; j < fieldValue.length; ++j) {
                    valueBuilder.append(fieldValue[j]);
                    if (j != fieldValue.length - 1) {
                        valueBuilder.append(",");
                    }
                }
                if (i != fields.size() - 1) {
                    valueBuilder.append(";");
                }
            }
        }
        theValue.set(Strings.nullToEmpty(valueBuilder.toString()));
  }
}
