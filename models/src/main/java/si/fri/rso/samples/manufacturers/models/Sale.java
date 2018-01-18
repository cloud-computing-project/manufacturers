package si.fri.rso.samples.manufacturers.models;

public class Sale {

        private String id;

        private String productId;

        private String oldPrice;

        private String newPrice;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getProductId() {
                return productId;
        }

        public void setProductId(String productId) {
                this.productId = productId;
        }

        public String getOldPrice() {
                return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
                this.oldPrice = oldPrice;
        }

        public String getNewPrice() {
                return newPrice;
        }

        public void setNewPrice(String newPrice) {
                this.newPrice = newPrice;
        }
}
