package sorting

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"

	"gonum.org/v1/plot"
	"gonum.org/v1/plot/plotter"
	"gonum.org/v1/plot/plotutil"
	"gonum.org/v1/plot/vg"
)

type AlgorithmData struct {
	Sizes     []int
	QuickRec  []float64
	QuickIter []float64
	Insertion []float64
	Builtin   []float64
}

func main() {
	// Read test results from file or standard input
	data, err := readTestResults("test_results.txt")
	if err != nil {
		log.Fatalf("Error reading test results: %v", err)
	}

	// Create graphs
	err = createAllAlgorithmsGraph(data)
	if err != nil {
		log.Fatalf("Error creating all algorithms graph: %v", err)
	}

	err = createQuickVsBuiltinGraph(data)
	if err != nil {
		log.Fatalf("Error creating quick vs builtin graph: %v", err)
	}

	fmt.Println("Graphs created successfully!")
}

func readTestResults(filename string) (*AlgorithmData, error) {
	file, err := os.Open(filename)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	data := &AlgorithmData{}
	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := scanner.Text()
		if strings.HasPrefix(line, "Size: ") {
			size, err := strconv.Atoi(strings.TrimPrefix(line, "Size: "))
			if err != nil {
				return nil, err
			}
			data.Sizes = append(data.Sizes, size)

			// Read the next 3-4 lines for algorithm times
			for i := 0; i < 4; i++ {
				if !scanner.Scan() {
					break
				}
				timeLine := scanner.Text()
				processTimeLine(timeLine, data, size)
			}
		}
	}

	if err := scanner.Err(); err != nil {
		return nil, err
	}

	return data, nil
}

func processTimeLine(line string, data *AlgorithmData, size int) {
	line = strings.TrimSpace(line)
	if line == "" {
		return
	}

	parts := strings.Split(line, ":")
	if len(parts) < 2 {
		return
	}

	algorithm := strings.TrimSpace(parts[0])
	timeStr := strings.TrimSpace(parts[1])

	// Parse the duration (e.g., "1.234567ms" or "12.345678µs")
	var duration float64
	if strings.Contains(timeStr, "µs") {
		val, _ := strconv.ParseFloat(strings.TrimSuffix(timeStr, "µs"), 64)
		duration = val / 1000 // convert µs to ms
	} else if strings.Contains(timeStr, "ms") {
		duration, _ = strconv.ParseFloat(strings.TrimSuffix(timeStr, "ms"), 64)
	} else if strings.Contains(timeStr, "s") {
		val, _ := strconv.ParseFloat(strings.TrimSuffix(timeStr, "s"), 64)
		duration = val * 1000 // convert s to ms
	}

	switch {
	case strings.Contains(algorithm, "QuickSortRecursive"):
		data.QuickRec = append(data.QuickRec, duration)
	case strings.Contains(algorithm, "QuickSort (iter)"):
		data.QuickIter = append(data.QuickIter, duration)
	case strings.Contains(algorithm, "InsertionSort"):
		data.Insertion = append(data.Insertion, duration)
	case strings.Contains(algorithm, "sort.Ints"):
		data.Builtin = append(data.Builtin, duration)
	}
}

func createAllAlgorithmsGraph(data *AlgorithmData) error {
	p := plot.New()

	p.Title.Text = "Sorting Algorithm Performance Comparison"
	p.X.Label.Text = "Array Size"
	p.Y.Label.Text = "Time (ms)"
	p.Y.Scale = plot.LogScale{}
	p.Y.Tick.Marker = plot.LogTicks{}

	pointsQuickRec := make(plotter.XYs, len(data.Sizes))
	pointsQuickIter := make(plotter.XYs, len(data.Sizes))
	pointsInsertion := make(plotter.XYs, 0)
	pointsBuiltin := make(plotter.XYs, len(data.Sizes))

	for i, size := range data.Sizes {
		pointsQuickRec[i].X = float64(size)
		pointsQuickRec[i].Y = data.QuickRec[i]

		pointsQuickIter[i].X = float64(size)
		pointsQuickIter[i].Y = data.QuickIter[i]

		pointsBuiltin[i].X = float64(size)
		pointsBuiltin[i].Y = data.Builtin[i]

		if i < len(data.Insertion) {
			pointsInsertion = append(pointsInsertion, plotter.XY{
				X: float64(size),
				Y: data.Insertion[i],
			})
		}
	}

	err := plotutil.AddLinePoints(p,
		"QuickSort Recursive", pointsQuickRec,
		"QuickSort Iterative", pointsQuickIter,
		"InsertionSort", pointsInsertion,
		"sort.Ints (Builtin)", pointsBuiltin)
	if err != nil {
		return err
	}

	// Save the plot to a PNG file
	if err := p.Save(12*vg.Inch, 8*vg.Inch, "all_algorithms.png"); err != nil {
		return err
	}

	return nil
}

func createQuickVsBuiltinGraph(data *AlgorithmData) error {
	p := plot.New()

	p.Title.Text = "QuickSort vs Builtin Sort Performance"
	p.X.Label.Text = "Array Size"
	p.Y.Label.Text = "Time (ms)"
	p.Y.Scale = plot.LogScale{}
	p.Y.Tick.Marker = plot.LogTicks{}

	pointsQuickRec := make(plotter.XYs, len(data.Sizes))
	pointsQuickIter := make(plotter.XYs, len(data.Sizes))
	pointsBuiltin := make(plotter.XYs, len(data.Sizes))

	for i, size := range data.Sizes {
		pointsQuickRec[i].X = float64(size)
		pointsQuickRec[i].Y = data.QuickRec[i]

		pointsQuickIter[i].X = float64(size)
		pointsQuickIter[i].Y = data.QuickIter[i]

		pointsBuiltin[i].X = float64(size)
		pointsBuiltin[i].Y = data.Builtin[i]
	}

	err := plotutil.AddLinePoints(p,
		"QuickSort Recursive", pointsQuickRec,
		"QuickSort Iterative", pointsQuickIter,
		"sort.Ints (Builtin)", pointsBuiltin)
	if err != nil {
		return err
	}

	// Save the plot to a PNG file
	if err := p.Save(12*vg.Inch, 8*vg.Inch, "quick_vs_builtin.png"); err != nil {
		return err
	}

	return nil
}
