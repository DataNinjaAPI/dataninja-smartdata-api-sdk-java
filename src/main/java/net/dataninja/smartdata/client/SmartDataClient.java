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

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class SmartDataClient {

    private static final SmartDataConfig config = new SmartDataConfig();
    SmartDataApi smartData;

    public SmartDataClient() {
        // Set the standard headers for all service APIs
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(retrofit.RequestInterceptor.RequestFacade request) {
                request.addHeader("X-Mashape-Key", config.getMashapeKey());
                request.addHeader("Accept", "application/json");
            }
        };

        System.out.println(config.getMashapeKey());

        // Create a REST adapter which points to the SmartData API endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(config.getApiUrl())
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        System.out.println(msg);
                    }
                })
                .build();

        // Create an instance of our SmartData API interface.
        this.smartData = restAdapter.create(SmartDataApi.class);
    }

    public SmartDataApi getClient() {
        return smartData;
    }
}

