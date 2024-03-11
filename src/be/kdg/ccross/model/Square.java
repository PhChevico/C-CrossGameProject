    package be.kdg.ccross.model;

    public class Square {
        private boolean status;
        private Player ownership;
        private int column;
        private int row;

        public Square(int column, int row){
            this.column = column;
            this.row = row;
        }


        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public Player getOwnership() {
            return ownership;
        }

        public void setOwnership(Player ownership) {
            this.ownership = ownership;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        @Override
        public String toString() {
            return "Square{" +
                    "status=" + status +
                    ", ownership=" + ownership +
                    '}';
        }



    }
