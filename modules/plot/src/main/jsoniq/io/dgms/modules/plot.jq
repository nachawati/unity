jsoniq version "1.0-dg";

(:
 : بسم الله الرحمن الرحيم
 :
 : In the name of Allah, the Most Compassionate, the Most Merciful
 :
 : This file is part of Unity DGMS <https://www.dgms.io/>
 :
 : Unity DGMS is free software; redistribution and use in source and binary
 : forms, with or without modification, are permitted provided that the
 : following conditions are met:
 :
 : 1. Redistributions of source code must retain the above notice, this list of
 :    conditions and the following disclaimer.
 :
 : 2. Redistributions in binary form must reproduce the above notice, this list
 :    of conditions and the following disclaimer in the documentation and/or
 :    other materials provided with the distribution.
 :
 : THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHORS DISCLAIM ALL WARRANTIES
 : WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 : MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
 : SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 : WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 : OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 : CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 :)

module namespace plot = "http://dgms.io/modules/plot";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

(:~
 : Plot the autocorrelation of x.
 : @see matplotlib.pyplot.acorr(x, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:acorr() external;

(:~
 : Plot the angle spectrum.
 : @see matplotlib.pyplot.angle_spectrum(x, Fs=None, Fc=None, window=None, pad_to=None, sides=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:angle-spectrum() external;

(:~
 : Annotate the point xy with text s.
 : @see matplotlib.pyplot.annotate(s, xy, *args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:annotate() external;

(:~
 : Add an arrow to the axes.
 : @see matplotlib.pyplot.arrow(x, y, dx, dy, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:arrow() external;

(:~
 : Autoscale the axis view to the data (toggle).
 : @see matplotlib.pyplot.autoscale(enable=True, axis='both', tight=None)
 :)
declare %public %an:deterministic %an:variadic function plot:autoscale() external;

(:~
 : Add an axes to the current figure and make it the current axes.
 : @see matplotlib.pyplot.axes(arg=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:axes() external;

(:~
 : Add a horizontal line across the axis.
 : @see matplotlib.pyplot.axhline(y=0, xmin=0, xmax=1, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:axhline() external;

(:~
 : Add a horizontal span (rectangle) across the axis.
 : @see matplotlib.pyplot.axhspan(ymin, ymax, xmin=0, xmax=1, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:axhspan() external;

(:~
 : Convenience method to get or set some axis properties.
 : @see matplotlib.pyplot.axis(*v, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:axis() external;

(:~
 : Add a vertical line across the axes.
 : @see matplotlib.pyplot.axvline(x=0, ymin=0, ymax=1, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:axvline() external;

(:~
 : Add a vertical span (rectangle) across the axes.
 : @see matplotlib.pyplot.axvspan(xmin, xmax, ymin=0, ymax=1, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:axvspan() external;

(:~
 : Make a bar plot.
 : @see matplotlib.pyplot.bar(x, height, width=0.8, bottom=None, *, align='center', data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:bar() external;

(:~
 : Plot a 2-D field of barbs.
 : @see matplotlib.pyplot.barbs(*args, data=None, **kw)
 :)
declare %public %an:deterministic %an:variadic function plot:barbs() external;

(:~
 : Make a horizontal bar plot.
 : @see matplotlib.pyplot.barh(y, width, height=0.8, left=None, *, align='center', **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:barh() external;

(:~
 : Turn the axes box on or off on the current axes.
 : @see matplotlib.pyplot.box(on=None)
 :)
declare %public %an:deterministic %an:variadic function plot:box() external;

(:~
 : Make a box and whisker plot.
 : @see matplotlib.pyplot.boxplot(x, notch=None, sym=None, vert=None, whis=None, positions=None, widths=None, patch_artist=None, bootstrap=None, usermedians=None, conf_intervals=None, meanline=None, showmeans=None, showcaps=None, showbox=None, showfliers=None, boxprops=None, labels=None, flierprops=None, medianprops=None, meanprops=None, capprops=None, whiskerprops=None, manage_xticks=True, autorange=False, zorder=None, *, data=None)
 :)
declare %public %an:deterministic %an:variadic function plot:boxplot() external;

(:~
 : Plot a horizontal sequence of rectangles.
 : @see matplotlib.pyplot.broken_barh(xranges, yrange, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:broken-barh() external;

(:~
 : Clear the current axes.
 : @see matplotlib.pyplot.cla()
 :)
declare %public %an:deterministic %an:variadic function plot:cla() external;

(:~
 : Label a contour plot.
 : @see matplotlib.pyplot.clabel(CS, *args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:clabel() external;

(:~
 : Clear the current figure.
 : @see matplotlib.pyplot.clf()
 :)
declare %public %an:deterministic %an:variadic function plot:clf() external;

(:~
 : Set the color limits of the current image.
 : @see matplotlib.pyplot.clim(vmin=None, vmax=None)
 :)
declare %public %an:deterministic %an:variadic function plot:clim() external;

(:~
 : Close a figure window.
 : @see matplotlib.pyplot.close(fig=None)
 :)
declare %public %an:deterministic %an:variadic function plot:close() external;

(:~
 : Plot the coherence between x and y.
 : @see matplotlib.pyplot.cohere(x, y, NFFT=256, Fs=2, Fc=0, detrend=&lt;function detrend_none&gt;, window=&lt;function window_hanning&gt;, noverlap=0, pad_to=None, sides='default', scale_by_freq=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:cohere() external;

(:~
 : Add a colorbar to a plot.
 : @see matplotlib.pyplot.colorbar(mappable=None, cax=None, ax=None, **kw)
 :)
declare %public %an:deterministic %an:variadic function plot:colorbar() external;

(:~
 : Plot contours.
 : @see matplotlib.pyplot.contour(*args, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:contour() external;

(:~
 : Plot contours.
 : @see matplotlib.pyplot.contourf(*args, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:contourf() external;

(:~
 : Plot the cross-spectral density.
 : @see matplotlib.pyplot.csd(x, y, NFFT=None, Fs=None, Fc=None, detrend=None, window=None, noverlap=None, pad_to=None, sides=None, scale_by_freq=None, return_line=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:csd() external;

(:~
 : Remove the Axes ax (defaulting to the current axes) from its figure.
 : @see matplotlib.pyplot.delaxes(ax=None)
 :)
declare %public %an:deterministic %an:variadic function plot:delaxes() external;

(:~
 : Redraw the current figure.
 : @see matplotlib.pyplot.draw()
 :)
declare %public %an:deterministic %an:variadic function plot:draw() external;

(:~
 : Plot y versus x as lines and/or markers with attached errorbars.
 : @see matplotlib.pyplot.errorbar(x, y, yerr=None, xerr=None, fmt='', ecolor=None, elinewidth=None, capsize=None, barsabove=False, lolims=False, uplims=False, xlolims=False, xuplims=False, errorevery=1, capthick=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:errorbar() external;

(:~
 : Plot identical parallel lines at the given positions.
 : @see matplotlib.pyplot.eventplot(positions, orientation='horizontal', lineoffsets=1, linelengths=1, linewidths=None, colors=None, linestyles='solid', *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:eventplot() external;

(:~
 : Add a non-resampled image to the figure.
 : @see matplotlib.pyplot.figimage(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:figimage() external;

(:~
 : Place a legend in the figure.
 : @see matplotlib.pyplot.figlegend(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:figlegend() external;

(:~
 : Return whether the figure with the given id exists.
 : @see matplotlib.pyplot.fignum_exists(num)
 :)
declare %public %an:deterministic %an:variadic function plot:fignum-exists() external;

(:~
 : Add text to figure.
 : @see matplotlib.pyplot.figtext(x, y, s, *args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:figtext() external;

(:~
 : Create a new figure.
 : @see matplotlib.pyplot.figure(num=None, figsize=None, dpi=None, facecolor=None, edgecolor=None, frameon=True, FigureClass=&lt;class 'matplotlib.figure.Figure'&gt;, clear=False, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:figure() external;

(:~
 : Plot filled polygons.
 : @see matplotlib.pyplot.fill(*args, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:fill() external;

(:~
 : Fill the area between two horizontal curves.
 : @see matplotlib.pyplot.fill_between(x, y1, y2=0, where=None, interpolate=False, step=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:fill-between() external;

(:~
 : Fill the area between two vertical curves.
 : @see matplotlib.pyplot.fill_betweenx(y, x1, x2=0, where=None, step=None, interpolate=False, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:fill-betweenx() external;

(:~
 : Find artist objects.
 : @see matplotlib.pyplot.findobj(o=None, match=None, include_self=True)
 :)
declare %public %an:deterministic %an:variadic function plot:findobj() external;

(:~
 : Get the current Axes instance on the current figure matching the given keyword args, or create one.
 : @see matplotlib.pyplot.gca(**kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:gca() external;

(:~
 : Get a reference to the current figure.
 : @see matplotlib.pyplot.gcf()
 :)
declare %public %an:deterministic %an:variadic function plot:gcf() external;

(:~
 : Get the current colorable artist.
 : @see matplotlib.pyplot.gci()
 :)
declare %public %an:deterministic %an:variadic function plot:gci() external;

(:~
 : Return a list of existing figure labels.
 : @see matplotlib.pyplot.get_figlabels()
 :)
declare %public %an:deterministic %an:variadic function plot:get-figlabels() external;

(:~
 : Return a list of existing figure numbers.
 : @see matplotlib.pyplot.get_fignums()
 :)
declare %public %an:deterministic %an:variadic function plot:get-fignums() external;

(:~
 : Configure the grid lines.
 : @see matplotlib.pyplot.grid(b=None, which='major', axis='both', **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:grid() external;

(:~
 : Make a hexagonal binning plot.
 : @see matplotlib.pyplot.hexbin(x, y, C=None, gridsize=100, bins=None, xscale='linear', yscale='linear', extent=None, cmap=None, norm=None, vmin=None, vmax=None, alpha=None, linewidths=None, edgecolors='face', reduce_C_function=&lt;function mean&gt;, mincnt=None, marginals=False, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:hexbin() external;

(:~
 : Plot a histogram.
 : @see matplotlib.pyplot.hist(x, bins=None, range=None, density=None, weights=None, cumulative=False, bottom=None, histtype='bar', align='mid', orientation='vertical', rwidth=None, log=False, color=None, label=None, stacked=False, normed=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:hist() external;

(:~
 : Make a 2D histogram plot.
 : @see matplotlib.pyplot.hist2d(x, y, bins=10, range=None, normed=False, weights=None, cmin=None, cmax=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:hist2d() external;

(:~
 : Plot horizontal lines at each y from xmin to xmax.
 : @see matplotlib.pyplot.hlines(y, xmin, xmax, colors='k', linestyles='solid', label='', *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:hlines() external;

(:~
 : Read an image from a file into an array.
 : @see matplotlib.pyplot.imread(fname, format=None)
 :)
declare %public %an:deterministic %an:variadic function plot:imread() external;

(:~
 : Save an array as in image file.
 : @see matplotlib.pyplot.imsave(fname, arr, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:imsave() external;

(:~
 : Display an image, i.e.
 : @see matplotlib.pyplot.imshow(X, cmap=None, norm=None, aspect=None, interpolation=None, alpha=None, vmin=None, vmax=None, origin=None, extent=None, shape=None, filternorm=1, filterrad=4.0, imlim=None, resample=None, url=None, *, data=None, **kwargs)[source]
 :)
declare %public %an:deterministic %an:variadic function plot:imshow() external;

(:~
 : Install a repl display hook so that any stale figure are automatically redrawn when control is returned to the repl.
 : @see matplotlib.pyplot.install_repl_displayhook()
 :)
declare %public %an:deterministic %an:variadic function plot:install-repl-displayhook() external;

(:~
 : Turn the interactive mode off.
 : @see matplotlib.pyplot.ioff()
 :)
declare %public %an:deterministic %an:variadic function plot:ioff() external;

(:~
 : Turn the interactive mode on.
 : @see matplotlib.pyplot.ion()
 :)
declare %public %an:deterministic %an:variadic function plot:ion() external;

(:~
 : Return the status of interactive mode.
 : @see matplotlib.pyplot.isinteractive()
 :)
declare %public %an:deterministic %an:variadic function plot:isinteractive() external;

(:~
 : Place a legend on the axes.
 : @see matplotlib.pyplot.legend(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:legend() external;

(:~
 : Control behavior of tick locators.
 : @see matplotlib.pyplot.locator_params(axis='both', tight=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:locator-params() external;

(:~
 : Make a plot with log scaling on both the x and y axis.
 : @see matplotlib.pyplot.loglog(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:loglog() external;

(:~
 : Plot the magnitude spectrum.
 : @see matplotlib.pyplot.magnitude_spectrum(x, Fs=None, Fc=None, window=None, pad_to=None, sides=None, scale=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:magnitude-spectrum() external;

(:~
 : Set or retrieve autoscaling margins.
 : @see matplotlib.pyplot.margins(*margins, x=None, y=None, tight=True)
 :)
declare %public %an:deterministic %an:variadic function plot:margins() external;

(:~
 : Display an array as a matrix in a new figure window.
 : @see matplotlib.pyplot.matshow(A, fignum=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:matshow() external;

(:~
 : Remove minor ticks from the axes.
 : @see matplotlib.pyplot.minorticks_off()
 :)
declare %public %an:deterministic %an:variadic function plot:minorticks-off() external;

(:~
 : Display minor ticks on the axes.
 : @see matplotlib.pyplot.minorticks_on()
 :)
declare %public %an:deterministic %an:variadic function plot:minorticks-on() external;

(:~
 : Pause for interval seconds.
 : @see matplotlib.pyplot.pause(interval)
 :)
declare %public %an:deterministic %an:variadic function plot:pause() external;

(:~
 : Create a pseudocolor plot with a non-regular rectangular grid.
 : @see matplotlib.pyplot.pcolor(*args, alpha=None, norm=None, cmap=None, vmin=None, vmax=None, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:pcolor() external;

(:~
 : Create a pseudocolor plot with a non-regular rectangular grid.
 : @see matplotlib.pyplot.pcolormesh(*args, alpha=None, norm=None, cmap=None, vmin=None, vmax=None, shading='flat', antialiased=False, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:pcolormesh() external;

(:~
 : Plot the phase spectrum.
 : @see matplotlib.pyplot.phase_spectrum(x, Fs=None, Fc=None, window=None, pad_to=None, sides=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:phase-spectrum() external;

(:~
 : Plot a pie chart.
 : @see matplotlib.pyplot.pie(x, explode=None, labels=None, colors=None, autopct=None, pctdistance=0.6, shadow=False, labeldistance=1.1, startangle=None, radius=None, counterclock=True, wedgeprops=None, textprops=None, center=(0, 0), frame=False, rotatelabels=False, *, data=None)
 :)
declare %public %an:deterministic %an:variadic function plot:pie() external;

(:~
 : Plot y versus x as lines and/or markers.
 : @see matplotlib.pyplot.plot(*args, scalex=True, scaley=True, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:plot() external;

(:~
 : Plot data that contains dates.
 : @see matplotlib.pyplot.plot_date(x, y, fmt='o', tz=None, xdate=True, ydate=False, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:plot-date() external;

(:~
 : Plot the data in a file.
 : @see matplotlib.pyplot.plotfile(fname, cols=(0, ), plotfuncs=None, comments='#', skiprows=0, checkrows=5, delimiter=', ', names=None, subplots=True, newfig=True, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:plotfile() external;

(:~
 : Make a polar plot.
 : @see matplotlib.pyplot.polar(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:polar() external;

(:~
 : Plot the power spectral density.
 : @see matplotlib.pyplot.psd(x, NFFT=None, Fs=None, Fc=None, detrend=None, window=None, noverlap=None, pad_to=None, sides=None, scale_by_freq=None, return_line=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:psd() external;

(:~
 : Plot a 2-D field of arrows.
 : @see matplotlib.pyplot.quiver(*args, data=None, **kw)
 :)
declare %public %an:deterministic %an:variadic function plot:quiver() external;

(:~
 : Add a key to a quiver plot.
 : @see matplotlib.pyplot.quiverkey(Q, X, Y, U, label, **kw)
 :)
declare %public %an:deterministic %an:variadic function plot:quiverkey() external;

(:~
 : Set the current rc params.
 : @see matplotlib.pyplot.rc(group, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:rc() external;

(:~
 : Return a context manager for managing rc settings.
 : @see matplotlib.pyplot.rc_context(rc=None, fname=None)
 :)
declare %public %an:deterministic %an:variadic function plot:rc-context() external;

(:~
 : Restore the rc params from Matplotlib's internal default style.
 : @see matplotlib.pyplot.rcdefaults()
 :)
declare %public %an:deterministic %an:variadic function plot:rcdefaults() external;

(:~
 : Get or set the radial gridlines on the current polar plot.
 : @see matplotlib.pyplot.rgrids(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:rgrids() external;

(:~
 : Save the current figure.
 : @see matplotlib.pyplot.savefig(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:savefig() external;

(:~
 : Set the current Axes instance to ax.
 : @see matplotlib.pyplot.sca(ax)
 :)
declare %public %an:deterministic %an:variadic function plot:sca() external;

(:~
 : A scatter plot of y vs x with varying marker size and/or color.
 : @see matplotlib.pyplot.scatter(x, y, s=None, c=None, marker=None, cmap=None, norm=None, vmin=None, vmax=None, alpha=None, linewidths=None, verts=None, edgecolors=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:scatter() external;

(:~
 : Set the current image.
 : @see matplotlib.pyplot.sci(im)
 :)
declare %public %an:deterministic %an:variadic function plot:sci() external;

(:~
 : Make a plot with log scaling on the x axis.
 : @see matplotlib.pyplot.semilogx(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:semilogx() external;

(:~
 : Make a plot with log scaling on the y axis.
 : @see matplotlib.pyplot.semilogy(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:semilogy() external;

(:~
 : Set the default colormap.
 : @see matplotlib.pyplot.set_cmap(cmap)
 :)
declare %public %an:deterministic %an:variadic function plot:set-cmap() external;

(:~
 : Set a property on an artist object.
 : @see matplotlib.pyplot.setp(obj, *args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:setp() external;

(:~
 : Display a figure.
 : @see matplotlib.pyplot.show(*args, **kw)
 :)
declare %public %an:deterministic %an:variadic function plot:show() external;

(:~
 : Plot a spectrogram.
 : @see matplotlib.pyplot.specgram(x, NFFT=None, Fs=None, Fc=None, detrend=None, window=None, noverlap=None, cmap=None, xextent=None, pad_to=None, sides=None, scale_by_freq=None, mode=None, scale=None, vmin=None, vmax=None, *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:specgram() external;

(:~
 : Plot the sparsity pattern of a 2D array.
 : @see matplotlib.pyplot.spy(Z, precision=0, marker=None, markersize=None, aspect='equal', origin='upper', **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:spy() external;

(:~
 : Draw a stacked area plot.
 : @see matplotlib.pyplot.stackplot(x, *args, data=None, **kwargs)[source]
 :)
declare %public %an:deterministic %an:variadic function plot:stackplot() external;

(:~
 : Create a stem plot.
 : @see matplotlib.pyplot.stem(*args, linefmt=None, markerfmt=None, basefmt=None, bottom=0, label=None, data=None)
 :)
declare %public %an:deterministic %an:variadic function plot:stem() external;

(:~
 : Make a step plot.
 : @see matplotlib.pyplot.step(x, y, *args, where='pre', data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:step() external;

(:~
 : Draw streamlines of a vector flow.
 : @see matplotlib.pyplot.streamplot(x, y, u, v, density=1, linewidth=None, color=None, cmap=None, norm=None, arrowsize=1, arrowstyle='-|&gt;', minlength=0.1, transform=None, zorder=None, start_points=None, maxlength=4.0, integration_direction='both', *, data=None)
 :)
declare %public %an:deterministic %an:variadic function plot:streamplot() external;

(:~
 : Add a subplot to the current figure.
 : @see matplotlib.pyplot.subplot(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:subplot() external;

(:~
 : Create an axis at specific location inside a regular grid.
 : @see matplotlib.pyplot.subplot2grid(shape, loc, rowspan=1, colspan=1, fig=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:subplot2grid() external;

(:~
 : Launch a subplot tool window for a figure.
 : @see matplotlib.pyplot.subplot_tool(targetfig=None)
 :)
declare %public %an:deterministic %an:variadic function plot:subplot-tool() external;

(:~
 : Create a figure and a set of subplots.
 : @see matplotlib.pyplot.subplots(nrows=1, ncols=1, sharex=False, sharey=False, squeeze=True, subplot_kw=None, gridspec_kw=None, **fig_kw)
 :)
declare %public %an:deterministic %an:variadic function plot:subplots() external;

(:~
 : Tune the subplot layout.
 : @see matplotlib.pyplot.subplots_adjust(left=None, bottom=None, right=None, top=None, wspace=None, hspace=None)
 :)
declare %public %an:deterministic %an:variadic function plot:subplots-adjust() external;

(:~
 : Add a centered title to the figure.
 : @see matplotlib.pyplot.suptitle(t, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:suptitle() external;

(:~
 : Close all open figures and set the Matplotlib backend.
 : @see matplotlib.pyplot.switch_backend(newbackend)
 :)
declare %public %an:deterministic %an:variadic function plot:switch-backend() external;

(:~
 : Add a table to the current axes.
 : @see matplotlib.pyplot.table(**kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:table() external;

(:~
 : Add text to the axes.
 : @see matplotlib.pyplot.text(x, y, s, fontdict=None, withdash=False, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:text() external;

(:~
 : Get or set the theta gridlines on the current polar plot.
 : @see matplotlib.pyplot.thetagrids(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:thetagrids() external;

(:~
 : Change the appearance of ticks, tick labels, and gridlines.
 : @see matplotlib.pyplot.tick_params(axis='both', **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:tick-params() external;

(:~
 : Change the ScalarFormatter used by default for linear axes.
 : @see matplotlib.pyplot.ticklabel_format(*, axis='both', style='', scilimits=None, useOffset=None, useLocale=None, useMathText=None)
 :)
declare %public %an:deterministic %an:variadic function plot:ticklabel-format() external;

(:~
 : Automatically adjust subplot parameters to give specified padding.
 : @see matplotlib.pyplot.tight_layout(pad=1.08, h_pad=None, w_pad=None, rect=None)
 :)
declare %public %an:deterministic %an:variadic function plot:tight-layout() external;

(:~
 : Set a title for the axes.
 : @see matplotlib.pyplot.title(label, fontdict=None, loc='center', pad=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:title() external;

(:~
 : Draw contours on an unstructured triangular grid.
 : @see matplotlib.pyplot.tricontour(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:tricontour() external;

(:~
 : Draw contours on an unstructured triangular grid.
 : @see matplotlib.pyplot.tricontourf(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:tricontourf() external;

(:~
 : Create a pseudocolor plot of an unstructured triangular grid.
 : @see matplotlib.pyplot.tripcolor(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:tripcolor() external;

(:~
 : Draw a unstructured triangular grid as lines and/or markers.
 : @see matplotlib.pyplot.triplot(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:triplot() external;

(:~
 : Make a second axes that shares the x-axis.
 : @see matplotlib.pyplot.twinx(ax=None)
 :)
declare %public %an:deterministic %an:variadic function plot:twinx() external;

(:~
 : Make a second axes that shares the y-axis.
 : @see matplotlib.pyplot.twiny(ax=None)
 :)
declare %public %an:deterministic %an:variadic function plot:twiny() external;

(:~
 : Uninstall the matplotlib display hook.
 : @see matplotlib.pyplot.uninstall_repl_displayhook()
 :)
declare %public %an:deterministic %an:variadic function plot:uninstall-repl-displayhook() external;

(:~
 : Make a violin plot.
 : @see matplotlib.pyplot.violinplot(dataset, positions=None, vert=True, widths=0.5, showmeans=False, showextrema=True, showmedians=False, points=100, bw_method=None, *, data=None)
 :)
declare %public %an:deterministic %an:variadic function plot:violinplot() external;

(:~
 : Plot vertical lines.
 : @see matplotlib.pyplot.vlines(x, ymin, ymax, colors='k', linestyles='solid', label='', *, data=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:vlines() external;

(:~
 : Plot the cross correlation between x and y.
 : @see matplotlib.pyplot.xcorr(x, y, normed=True, detrend=&lt;function detrend_none&gt;, usevlines=True, maxlags=10, *, data=None, **kwargs)[source]
 :)
declare %public %an:deterministic %an:variadic function plot:xcorr() external;

(:~
 : Turn on xkcd sketch-style drawing mode.
 : @see matplotlib.pyplot.xkcd(scale=1, length=100, randomness=2)
 :)
declare %public %an:deterministic %an:variadic function plot:xkcd() external;

(:~
 : Set the label for the x-axis.
 : @see matplotlib.pyplot.xlabel(xlabel, fontdict=None, labelpad=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:xlabel() external;

(:~
 : Get or set the x limits of the current axes.
 : @see matplotlib.pyplot.xlim(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:xlim() external;

(:~
 : Set the x-axis scale.
 : @see matplotlib.pyplot.xscale(value, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:xscale() external;

(:~
 : Get or set the current tick locations and labels of the x-axis.
 : @see matplotlib.pyplot.xticks(ticks=None, labels=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:xticks() external;

(:~
 : Set the label for the y-axis.
 : @see matplotlib.pyplot.ylabel(ylabel, fontdict=None, labelpad=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:ylabel() external;

(:~
 : Get or set the y-limits of the current axes.
 : @see matplotlib.pyplot.ylim(*args, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:ylim() external;

(:~
 : Set the y-axis scale.
 : @see matplotlib.pyplot.yscale(value, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:yscale() external;

(:~
 : Get or set the current tick locations and labels of the y-axis.
 : @see matplotlib.pyplot.yticks(ticks=None, labels=None, **kwargs)
 :)
declare %public %an:deterministic %an:variadic function plot:yticks() external;
