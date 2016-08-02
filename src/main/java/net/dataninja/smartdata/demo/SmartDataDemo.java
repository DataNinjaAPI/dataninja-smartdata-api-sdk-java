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

import net.dataninja.smartdata.client.Results;
import net.dataninja.smartdata.client.SmartDataApi;
import net.dataninja.smartdata.client.SmartDataClient;

import java.io.Console;

/**
 * A simple command demo to show how to use the SmartData Java APIs
 */
public class SmartDataDemo {

    /**
     * Run through the SmartData APIs and show usage
     */
    public void process() {
        SmartDataApi client = new SmartDataClient().getClient();
        String input;

        Console cmdline = System.console();
        if (cmdline == null) {
            System.err.println("No console for command line demo.");
            System.exit(1);
        }

        // Find a Smart Data concept
        input = cmdline.readLine("Enter a concept to search: ");
        printResults(findByConceptDemo(client, input));

        // Find a Smart data category
        input = cmdline.readLine("Enter a category to search: ");
        printResults(findByCategoryDemo(client, input));

        // Find other concepts related to a concept
        input = cmdline.readLine("Enter a related concept to search: ");
        printResults(findRelatedByConceptDemo(client, input));

        // Find the popularity count for a concept
        input = cmdline.readLine("Enter a popularity concept to search: ");
        printResults(findPopularityByConceptDemo(client, input));

        // Find the categories of a given concept
        input = cmdline.readLine("Enter a concept id to find the parent categories: ");
        printResults(findCategoryListDemo(client, input));

        // Find the parent categories of a given category
        input = cmdline.readLine("Enter a category id to find the parent categories: ");
        printResults(findParentCategoryListDemo(client, input));

        // Find the child categories of a given category
        input = cmdline.readLine("Enter a category id to find the child categories: ");
        printResults(findChildCategoryListDemo(client, input));

        // Find the concepts that belong to a category
        input = cmdline.readLine("Enter a category id to find the child concepts: ");
        printResults(findConceptListDemo(client, input));
    }

    private void printResults(Results<Object> output) {
        // Print output to the console
        for (Object result : output.getResults()) {
            System.out.println(result.toString());
        }
    }

    public Results findConceptListDemo(SmartDataApi client, String input) {
        return client.findConceptList(Long.valueOf(input));
    }

    public Results findChildCategoryListDemo(SmartDataApi client, String input) {
        return client.findChildCategoryList(Long.valueOf(input));
    }

    public Results findParentCategoryListDemo(SmartDataApi client, String input) {
        return client.findParentCategoryList(Long.valueOf(input));
    }

    public Results findCategoryListDemo(SmartDataApi client, String input) {
        return client.findCategoryList(Long.valueOf(input));
    }

    public Results findPopularityByConceptDemo(SmartDataApi client, String input) {
        return client.findPopularityByConcept(input);
    }

    public Results findRelatedByConceptDemo(SmartDataApi client, String input) {
        return client.findRelatedByConcept(input);
    }

    public Results findByCategoryDemo(SmartDataApi client, String input) {
        return client.findByCategory(input);
    }

    public Results findByCategoryIdDemo(SmartDataApi client, Long input) {
        return client.findByCategoryId(input);
    }

    public Results findByConceptDemo(SmartDataApi client, String input) {
        return client.findByConcept(input);
    }

    public Results findByConceptIdDemo(SmartDataApi client, Long input) {
        return client.findByConceptId(input);
    }

    public static void main(String[] args) {
        SmartDataDemo demo = new SmartDataDemo();
        demo.process();
    }
}
