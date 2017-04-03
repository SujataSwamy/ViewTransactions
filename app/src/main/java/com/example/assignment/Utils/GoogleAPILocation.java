package com.example.assignment.Utils;

import android.location.Address;

import java.util.List;

/**
 * Created by sujata on 03/04/17.
 */



        import android.location.Address;

        import java.util.List;

/**
 * Created by divum on 13/6/16.
 */
public class GoogleAPILocation {

    /**
     * results : [{"address_components":[{"long_name":"Unnamed Road","short_name":"Unnamed Road","types":["route"]},{"long_name":"Vaddarahalli","short_name":"Vaddarahalli","types":["locality","political"]},{"long_name":"Ramanagara","short_name":"Ramanagara","types":["administrative_area_level_2","political"]},{"long_name":"Karnataka","short_name":"KA","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]},{"long_name":"562112","short_name":"562112","types":["postal_code"]}],"formatted_address":"Unnamed Road, Vaddarahalli, Karnataka 562112, India","geometry":{"bounds":{"northeast":{"lat":12.6717846,"lng":77.3441792},"southwest":{"lat":12.6705498,"lng":77.3421918}},"location":{"lat":12.6716183,"lng":77.3428685},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":12.6725161802915,"lng":77.3445344802915},"southwest":{"lat":12.6698182197085,"lng":77.3418365197085}}},"place_id":"ChIJ9fkjbjBQrjsRdgdNZkfgAUU","types":["route"]},{"address_components":[{"long_name":"Annahalli","short_name":"Annahalli","types":["locality","political"]},{"long_name":"Ramanagara","short_name":"Ramanagara","types":["administrative_area_level_2","political"]},{"long_name":"Karnataka","short_name":"KA","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Annahalli, Karnataka, India","geometry":{"bounds":{"northeast":{"lat":12.7590299,"lng":77.38614009999999},"southwest":{"lat":12.62896,"lng":77.31078}},"location":{"lat":12.6902449,"lng":77.3496708},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":12.7590299,"lng":77.38614009999999},"southwest":{"lat":12.62896,"lng":77.31078}}},"place_id":"ChIJdzPfG9tPrjsRDpPdlN7EHe0","types":["locality","political"]},{"address_components":[{"long_name":"562112","short_name":"562112","types":["postal_code"]},{"long_name":"Karnataka","short_name":"KA","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Karnataka 562112, India","geometry":{"bounds":{"northeast":{"lat":12.7976039,"lng":77.62019990000002},"southwest":{"lat":12.6540018,"lng":77.30239999999999}},"location":{"lat":12.6861235,"lng":77.5261225},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":12.7976039,"lng":77.62019990000002},"southwest":{"lat":12.6540018,"lng":77.30239999999999}}},"place_id":"ChIJ5-O9HIVErjsRuzf9fn0V7jE","types":["postal_code"]},{"address_components":[{"long_name":"Ramanagara","short_name":"Ramanagara","types":["administrative_area_level_2","political"]},{"long_name":"Karnataka","short_name":"KA","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Ramanagara, Karnataka, India","geometry":{"bounds":{"northeast":{"lat":13.1956001,"lng":77.6357199},"southwest":{"lat":12.2426201,"lng":77.07072010000002}},"location":{"lat":12.6002557,"lng":77.4701972},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":13.1956001,"lng":77.6357199},"southwest":{"lat":12.2426201,"lng":77.07072010000002}}},"place_id":"ChIJvfBTgRlarjsR57bS9GSdngY","types":["administrative_area_level_2","political"]},{"address_components":[{"long_name":"Karnataka","short_name":"KA","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Karnataka, India","geometry":{"bounds":{"northeast":{"lat":18.4411689,"lng":78.5860101},"southwest":{"lat":11.593352,"lng":74.0928802}},"location":{"lat":15.3172775,"lng":75.7138884},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":18.4411689,"lng":78.5860101},"southwest":{"lat":11.593352,"lng":74.0928802}}},"place_id":"ChIJj0i_N0xaozsR1Xx10YzS8UE","types":["administrative_area_level_1","political"]},{"address_components":[{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"India","geometry":{"bounds":{"northeast":{"lat":35.5087008,"lng":97.395561},"southwest":{"lat":6.7535159,"lng":68.1623859}},"location":{"lat":20.593684,"lng":78.96288},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":35.5087008,"lng":97.395561},"southwest":{"lat":6.7535159,"lng":68.16288519999999}}},"place_id":"ChIJkbeSa_BfYzARphNChaFPjNc","types":["country","political"]}]
     * status : OK
     */
    private List<Address> geoAddress;

    public  List<Address> getGeoAddress() {
        return geoAddress;
    }

    public  void setGeoAddress(List<Address> geoAddress) {
        this.geoAddress = geoAddress;
    }

    private String status;
    /**
     * address_components : [{"long_name":"Unnamed Road","short_name":"Unnamed Road","types":["route"]},{"long_name":"Vaddarahalli","short_name":"Vaddarahalli","types":["locality","political"]},{"long_name":"Ramanagara","short_name":"Ramanagara","types":["administrative_area_level_2","political"]},{"long_name":"Karnataka","short_name":"KA","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]},{"long_name":"562112","short_name":"562112","types":["postal_code"]}]
     * formatted_address : Unnamed Road, Vaddarahalli, Karnataka 562112, India
     * geometry : {"bounds":{"northeast":{"lat":12.6717846,"lng":77.3441792},"southwest":{"lat":12.6705498,"lng":77.3421918}},"location":{"lat":12.6716183,"lng":77.3428685},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":12.6725161802915,"lng":77.3445344802915},"southwest":{"lat":12.6698182197085,"lng":77.3418365197085}}}
     * place_id : ChIJ9fkjbjBQrjsRdgdNZkfgAUU
     * types : ["route"]
     */

    private List<ResultsBean> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String formatted_address;
        /**
         * bounds : {"northeast":{"lat":12.6717846,"lng":77.3441792},"southwest":{"lat":12.6705498,"lng":77.3421918}}
         * location : {"lat":12.6716183,"lng":77.3428685}
         * location_type : GEOMETRIC_CENTER
         * viewport : {"northeast":{"lat":12.6725161802915,"lng":77.3445344802915},"southwest":{"lat":12.6698182197085,"lng":77.3418365197085}}
         */

        private GeometryBean geometry;
        private String place_id;
        /**
         * long_name : Unnamed Road
         * short_name : Unnamed Road
         * types : ["route"]
         */

        private List<AddressComponentsBean> address_components;
        private List<String> types;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<AddressComponentsBean> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * northeast : {"lat":12.6717846,"lng":77.3441792}
             * southwest : {"lat":12.6705498,"lng":77.3421918}
             */

            private BoundsBean bounds;
            /**
             * lat : 12.6716183
             * lng : 77.3428685
             */

            private LocationBean location;
            private String location_type;
            /**
             * northeast : {"lat":12.6725161802915,"lng":77.3445344802915}
             * southwest : {"lat":12.6698182197085,"lng":77.3418365197085}
             */

            private ViewportBean viewport;

            public BoundsBean getBounds() {
                return bounds;
            }

            public void setBounds(BoundsBean bounds) {
                this.bounds = bounds;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class BoundsBean {
                /**
                 * lat : 12.6717846
                 * lng : 77.3441792
                 */

                private NortheastBean northeast;
                /**
                 * lat : 12.6705498
                 * lng : 77.3421918
                 */

                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }

            public static class LocationBean {
                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * lat : 12.6725161802915
                 * lng : 77.3445344802915
                 */

                private NortheastBean northeast;
                /**
                 * lat : 12.6698182197085
                 * lng : 77.3418365197085
                 */

                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponentsBean {
            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }
    }
}

