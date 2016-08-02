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

import retrofit.http.*;

import java.util.List;

/**
 * This interface defines an Client API for a SmartData API. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 */

public interface SmartDataApi {

	// Make the API call work with Mashape and local endpoints

	// The path where we expect the SmartData to live
	public static final String CONCEPT_SVC_PATH = "/concept";

	// The path where we expect the SmartData to live
	public static final String CATEGORY_SVC_PATH = "/category";

	@GET("/smartdata/concept/find_by_concept")
	Results<Concept> findByConcept(@Query("concept") String concept);

	@GET("/smartdata/concept/find_by_concept_id")
	Results<Concept> findByConceptId(@Query("concept_id") Long conceptId);

	@GET("/smartdata/category/find_by_category")
	Results<Category> findByCategory(@Query("category") String category);

	@GET("/smartdata/category/find_by_category_id")
	Results<Category> findByCategoryId(@Query("category_id") Long categoryId);

	@GET("/smartdata/relatedconcept/find_by_concept_id")
	Results<RelatedConcept> findRelatedByConceptId(@Query("concept_id") Long conceptId);

	@GET("/smartdata/relatedconcept/find_by_concept")
	Results<RelatedConcept> findRelatedByConcept(@Query("concept") String concept);

	@GET("/smartdata/conceptordering/find_by_concept_id_list")
	Results<Concept> findOrderingByConceptIdList(@Query("concept_id_list") List<Long> conceptIdList);

	@GET("/smartdata/conceptpopularity/find_by_concept_id")
	Results<Concept> findPopularityByConceptId(@Query("concept_id") Long conceptId);

	@GET("/smartdata/conceptpopularity/find_by_concept")
	Results<Concept> findPopularityByConcept(@Query("concept") String concept);

	@GET("/smartgraph/find_child_category_list")
	Results<Category> findChildCategoryList(@Query("category_id") Long categoryId);

	@GET("/smartgraph/find_concept_list")
	Results<Concept> findConceptList(@Query("category_id") Long categoryId);

	@GET("/smartgraph/find_parent_category_list")
	Results<Category> findParentCategoryList(@Query("category_id") Long categoryId);

	@GET("/smartgraph/find_category_list")
	Results<Category> findCategoryList(@Query("concept_id") Long conceptId);
}
