(ns dozenal-bus.core)

(defn- regular [i data]
  (->> data
       (take-while #(not= :sun %))
       (map #(vector (+ i 6) %))))

(defn- sun [i data]
  (->> data
       (drop-while #(not= :sun %))
       rest
       (map #(vector (+ i 6) %))))

(defn- mapcatv [f s]
  (vec
   (mapcat f (range) s)))

(defmacro defschedule [sym data]
  `(def ~sym {:regular ~(mapcatv regular data)
              :sun ~(mapcatv sun data)}))
