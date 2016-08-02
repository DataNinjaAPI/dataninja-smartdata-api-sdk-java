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

package net.dataninja.smartdata.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RelatedConcept implements Serializable {

    private long concept_id;

    private String concept_title;

    private List<Concept> related_concepts = new ArrayList<Concept>();

    public long getConcept_id() {
        return concept_id;
    }

    public void setConcept_id(long concept_id) {
        this.concept_id = concept_id;
    }

    public String getConcept_title() {
        return concept_title;
    }

    public void setConcept_title(String concept_title) {
        this.concept_title = concept_title;
    }

    public List<Concept> getRelated_concepts() {
        return related_concepts;
    }

    public void setRelated_concepts(List<Concept> related_concepts) {
        this.related_concepts = related_concepts;
    }

    @Override
    public String toString() {
        return "RelatedConcept{" +
                "concept_id=" + concept_id +
                ", concept_title='" + concept_title + '\'' +
                ", related_concepts=" + related_concepts +
                '}';
    }
}
