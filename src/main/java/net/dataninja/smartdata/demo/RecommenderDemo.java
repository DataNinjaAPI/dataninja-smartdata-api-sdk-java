/*
 * Copyright 2015 DOCOMO Innovations, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */

package net.dataninja.smartdata.demo;

import net.dataninja.smartdata.client.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * A demo app that shows how to develop a simple recommender from the Smartdata service
 */

public class RecommenderDemo {

    // Default values
    final static String IN_FILE_NAME = "demo/playlist.txt";
    final static String OUT_FILE_NAME = "demo/recommended.txt";
    final static Charset ENCODING = StandardCharsets.UTF_8;

    List<String> readPlaylistFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllLines(path, ENCODING);
    }

    /**
     *
     * @param recommended List of recommendations
     * @param fileName Output file name
     * @throws IOException
     */
    void writeRecommendedFile(List<String> recommended, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, recommended, ENCODING);
    }

    /**
     *
     * @param playList A list of artists, songs or albums
     * @return A list of recommended items
     */
    public List<String> recommend(List<String> playList) {
        return process(playList);
    }

    /**
     *
     * @param playFile Name of the input file that contains the playlist
     */
    public void recommend(String playFile, String outputFile) {
        try {
            writeRecommendedFile(process(readPlaylistFile(playFile)), outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> process(List<String> playList) {
        List<String> output = new ArrayList<String>();
        for (String item : playList) {
            output.add(item + "\t" + findRelatedConcepts(item));
        }
        return output;
    }

    /**
     *
     * @param input An artist, song or album
     * @return Return a list of recommended songs or albums for input
     */
    List<String> findRelatedConcepts(String input) {
        // Create an instance of SmartDataApi class
        SmartDataApi client = new SmartDataClient().getClient();

        // Call the findRelatedByConcept method to get the related concepts
        Results<RelatedConcept> relatedConcepts = client.findRelatedByConcept(input);
        List<String> output = new ArrayList<String>();

        // Iterate over the list of related concepts and find songs and albums
        for (RelatedConcept result : relatedConcepts.getResults()) {
            for (Concept concept : result.getRelated_concepts()) {
                if (concept.getConcept_title().contains("song") || concept.getConcept_title().contains("album")) {
                    System.out.println(concept.toString());
                    output.add(concept.getConcept_id() + "\t" + concept.getConcept_title() + "\t" + concept.getScore());
                }
            }
        }

        // The output contains songs or albums related to the input list
        return output;
    }

    /**
     *
     * Read a playlist from the file playlist.txt and write recommendations to the file recommended.txt
     */
    public static void main(String[] args) {
        RecommenderDemo recommender = new RecommenderDemo();
        recommender.recommend(IN_FILE_NAME, OUT_FILE_NAME);
    }
}
