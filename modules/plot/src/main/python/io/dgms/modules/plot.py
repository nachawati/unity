#
# بسم الله الرحمن الرحيم 
#
# In the name of Allah, the Most Compassionate, the Most Merciful
#
# This file is part of Unity DGMS <https://www.dgms.io/>
#
# Unity DGMS is free software; redistribution and use in source and binary
# forms, with or without modification, are permitted provided that the
# following conditions are met:
#
# 1. Redistributions of source code must retain the above notice, this list of
#    conditions and the following disclaimer.
#
# 2. Redistributions in binary form must reproduce the above notice, this list
#    of conditions and the following disclaimer in the documentation and/or
#    other materials provided with the distribution.
#
# THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHORS DISCLAIM ALL WARRANTIES
# WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
# MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
# SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
# WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
# OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
# CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
#

import matplotlib.pyplot as plt

def acorr(*args, **kwargs):
    """
    Plot the autocorrelation of x.
    @see matplotlib.pyplot.acorr(x, *, data=None, **kwargs)
    """
    return plt.acorr(*args, **kwargs);

def angle_spectrum(*args, **kwargs):
    """
    Plot the angle spectrum.
    @see matplotlib.pyplot.angle_spectrum(x, Fs=None, Fc=None, window=None, pad_to=None, sides=None, *, data=None, **kwargs)
    """
    return plt.angle_spectrum(*args, **kwargs);

def annotate(*args, **kwargs):
    """
    Annotate the point xy with text s.
    @see matplotlib.pyplot.annotate(s, xy, *args, **kwargs)
    """
    return plt.annotate(*args, **kwargs);

def arrow(*args, **kwargs):
    """
    Add an arrow to the axes.
    @see matplotlib.pyplot.arrow(x, y, dx, dy, **kwargs)
    """
    return plt.arrow(*args, **kwargs);

def autoscale(*args, **kwargs):
    """
    Autoscale the axis view to the data (toggle).
    @see matplotlib.pyplot.autoscale(enable=True, axis='both', tight=None)
    """
    return plt.autoscale(*args, **kwargs);

def axes(*args, **kwargs):
    """
    Add an axes to the current figure and make it the current axes.
    @see matplotlib.pyplot.axes(arg=None, **kwargs)
    """
    return plt.axes(*args, **kwargs);

def axhline(*args, **kwargs):
    """
    Add a horizontal line across the axis.
    @see matplotlib.pyplot.axhline(y=0, xmin=0, xmax=1, **kwargs)
    """
    return plt.axhline(*args, **kwargs);

def axhspan(*args, **kwargs):
    """
    Add a horizontal span (rectangle) across the axis.
    @see matplotlib.pyplot.axhspan(ymin, ymax, xmin=0, xmax=1, **kwargs)
    """
    return plt.axhspan(*args, **kwargs);

def axis(*args, **kwargs):
    """
    Convenience method to get or set some axis properties.
    @see matplotlib.pyplot.axis(*v, **kwargs)
    """
    return plt.axis(*args, **kwargs);

def axvline(*args, **kwargs):
    """
    Add a vertical line across the axes.
    @see matplotlib.pyplot.axvline(x=0, ymin=0, ymax=1, **kwargs)
    """
    return plt.axvline(*args, **kwargs);

def axvspan(*args, **kwargs):
    """
    Add a vertical span (rectangle) across the axes.
    @see matplotlib.pyplot.axvspan(xmin, xmax, ymin=0, ymax=1, **kwargs)
    """
    return plt.axvspan(*args, **kwargs);

def bar(*args, **kwargs):
    """
    Make a bar plot.
    @see matplotlib.pyplot.bar(x, height, width=0.8, bottom=None, *, align='center', data=None, **kwargs)
    """
    return plt.bar(*args, **kwargs);

def barbs(*args, **kwargs):
    """
    Plot a 2-D field of barbs.
    @see matplotlib.pyplot.barbs(*args, data=None, **kw)
    """
    return plt.barbs(*args, **kwargs);

def barh(*args, **kwargs):
    """
    Make a horizontal bar plot.
    @see matplotlib.pyplot.barh(y, width, height=0.8, left=None, *, align='center', **kwargs)
    """
    return plt.barh(*args, **kwargs);

def box(*args, **kwargs):
    """
    Turn the axes box on or off on the current axes.
    @see matplotlib.pyplot.box(on=None)
    """
    return plt.box(*args, **kwargs);

def boxplot(*args, **kwargs):
    """
    Make a box and whisker plot.
    @see matplotlib.pyplot.boxplot(x, notch=None, sym=None, vert=None, whis=None, positions=None, widths=None, patch_artist=None, bootstrap=None, usermedians=None, conf_intervals=None, meanline=None, showmeans=None, showcaps=None, showbox=None, showfliers=None, boxprops=None, labels=None, flierprops=None, medianprops=None, meanprops=None, capprops=None, whiskerprops=None, manage_xticks=True, autorange=False, zorder=None, *, data=None)
    """
    return plt.boxplot(*args, **kwargs);

def broken_barh(*args, **kwargs):
    """
    Plot a horizontal sequence of rectangles.
    @see matplotlib.pyplot.broken_barh(xranges, yrange, *, data=None, **kwargs)
    """
    return plt.broken_barh(*args, **kwargs);

def cla(*args, **kwargs):
    """
    Clear the current axes.
    @see matplotlib.pyplot.cla()
    """
    return plt.cla(*args, **kwargs);

def clabel(*args, **kwargs):
    """
    Label a contour plot.
    @see matplotlib.pyplot.clabel(CS, *args, **kwargs)
    """
    return plt.clabel(*args, **kwargs);

def clf(*args, **kwargs):
    """
    Clear the current figure.
    @see matplotlib.pyplot.clf()
    """
    return plt.clf(*args, **kwargs);

def clim(*args, **kwargs):
    """
    Set the color limits of the current image.
    @see matplotlib.pyplot.clim(vmin=None, vmax=None)
    """
    return plt.clim(*args, **kwargs);

def close(*args, **kwargs):
    """
    Close a figure window.
    @see matplotlib.pyplot.close(fig=None)
    """
    return plt.close(*args, **kwargs);

def cohere(*args, **kwargs):
    """
    Plot the coherence between x and y.
    @see matplotlib.pyplot.cohere(x, y, NFFT=256, Fs=2, Fc=0, detrend=<function detrend_none>, window=<function window_hanning>, noverlap=0, pad_to=None, sides='default', scale_by_freq=None, *, data=None, **kwargs)
    """
    return plt.cohere(*args, **kwargs);

def colorbar(*args, **kwargs):
    """
    Add a colorbar to a plot.
    @see matplotlib.pyplot.colorbar(mappable=None, cax=None, ax=None, **kw)
    """
    return plt.colorbar(*args, **kwargs);

def contour(*args, **kwargs):
    """
    Plot contours.
    @see matplotlib.pyplot.contour(*args, data=None, **kwargs)
    """
    return plt.contour(*args, **kwargs);

def contourf(*args, **kwargs):
    """
    Plot contours.
    @see matplotlib.pyplot.contourf(*args, data=None, **kwargs)
    """
    return plt.contourf(*args, **kwargs);

def csd(*args, **kwargs):
    """
    Plot the cross-spectral density.
    @see matplotlib.pyplot.csd(x, y, NFFT=None, Fs=None, Fc=None, detrend=None, window=None, noverlap=None, pad_to=None, sides=None, scale_by_freq=None, return_line=None, *, data=None, **kwargs)
    """
    return plt.csd(*args, **kwargs);

def delaxes(*args, **kwargs):
    """
    Remove the Axes ax (defaulting to the current axes) from its figure.
    @see matplotlib.pyplot.delaxes(ax=None)
    """
    return plt.delaxes(*args, **kwargs);

def draw(*args, **kwargs):
    """
    Redraw the current figure.
    @see matplotlib.pyplot.draw()
    """
    return plt.draw(*args, **kwargs);

def errorbar(*args, **kwargs):
    """
    Plot y versus x as lines and/or markers with attached errorbars.
    @see matplotlib.pyplot.errorbar(x, y, yerr=None, xerr=None, fmt='', ecolor=None, elinewidth=None, capsize=None, barsabove=False, lolims=False, uplims=False, xlolims=False, xuplims=False, errorevery=1, capthick=None, *, data=None, **kwargs)
    """
    return plt.errorbar(*args, **kwargs);

def eventplot(*args, **kwargs):
    """
    Plot identical parallel lines at the given positions.
    @see matplotlib.pyplot.eventplot(positions, orientation='horizontal', lineoffsets=1, linelengths=1, linewidths=None, colors=None, linestyles='solid', *, data=None, **kwargs)
    """
    return plt.eventplot(*args, **kwargs);

def figimage(*args, **kwargs):
    """
    Add a non-resampled image to the figure.
    @see matplotlib.pyplot.figimage(*args, **kwargs)
    """
    return plt.figimage(*args, **kwargs);

def figlegend(*args, **kwargs):
    """
    Place a legend in the figure.
    @see matplotlib.pyplot.figlegend(*args, **kwargs)
    """
    return plt.figlegend(*args, **kwargs);

def fignum_exists(*args, **kwargs):
    """
    Return whether the figure with the given id exists.
    @see matplotlib.pyplot.fignum_exists(num)
    """
    return plt.fignum_exists(*args, **kwargs);

def figtext(*args, **kwargs):
    """
    Add text to figure.
    @see matplotlib.pyplot.figtext(x, y, s, *args, **kwargs)
    """
    return plt.figtext(*args, **kwargs);

def figure(*args, **kwargs):
    """
    Create a new figure.
    @see matplotlib.pyplot.figure(num=None, figsize=None, dpi=None, facecolor=None, edgecolor=None, frameon=True, FigureClass=<class 'matplotlib.figure.Figure'>, clear=False, **kwargs)
    """
    return plt.figure(*args, **kwargs);

def fill(*args, **kwargs):
    """
    Plot filled polygons.
    @see matplotlib.pyplot.fill(*args, data=None, **kwargs)
    """
    return plt.fill(*args, **kwargs);

def fill_between(*args, **kwargs):
    """
    Fill the area between two horizontal curves.
    @see matplotlib.pyplot.fill_between(x, y1, y2=0, where=None, interpolate=False, step=None, *, data=None, **kwargs)
    """
    return plt.fill_between(*args, **kwargs);

def fill_betweenx(*args, **kwargs):
    """
    Fill the area between two vertical curves.
    @see matplotlib.pyplot.fill_betweenx(y, x1, x2=0, where=None, step=None, interpolate=False, *, data=None, **kwargs)
    """
    return plt.fill_betweenx(*args, **kwargs);

def findobj(*args, **kwargs):
    """
    Find artist objects.
    @see matplotlib.pyplot.findobj(o=None, match=None, include_self=True)
    """
    return plt.findobj(*args, **kwargs);

def gca(*args, **kwargs):
    """
    Get the current Axes instance on the current figure matching the given keyword args, or create one.
    @see matplotlib.pyplot.gca(**kwargs)
    """
    return plt.gca(*args, **kwargs);

def gcf(*args, **kwargs):
    """
    Get a reference to the current figure.
    @see matplotlib.pyplot.gcf()
    """
    return plt.gcf(*args, **kwargs);

def gci(*args, **kwargs):
    """
    Get the current colorable artist.
    @see matplotlib.pyplot.gci()
    """
    return plt.gci(*args, **kwargs);

def get_figlabels(*args, **kwargs):
    """
    Return a list of existing figure labels.
    @see matplotlib.pyplot.get_figlabels()
    """
    return plt.get_figlabels(*args, **kwargs);

def get_fignums(*args, **kwargs):
    """
    Return a list of existing figure numbers.
    @see matplotlib.pyplot.get_fignums()
    """
    return plt.get_fignums(*args, **kwargs);

def grid(*args, **kwargs):
    """
    Configure the grid lines.
    @see matplotlib.pyplot.grid(b=None, which='major', axis='both', **kwargs)
    """
    return plt.grid(*args, **kwargs);

def hexbin(*args, **kwargs):
    """
    Make a hexagonal binning plot.
    @see matplotlib.pyplot.hexbin(x, y, C=None, gridsize=100, bins=None, xscale='linear', yscale='linear', extent=None, cmap=None, norm=None, vmin=None, vmax=None, alpha=None, linewidths=None, edgecolors='face', reduce_C_function=<function mean>, mincnt=None, marginals=False, *, data=None, **kwargs)
    """
    return plt.hexbin(*args, **kwargs);

def hist(*args, **kwargs):
    """
    Plot a histogram.
    @see matplotlib.pyplot.hist(x, bins=None, range=None, density=None, weights=None, cumulative=False, bottom=None, histtype='bar', align='mid', orientation='vertical', rwidth=None, log=False, color=None, label=None, stacked=False, normed=None, *, data=None, **kwargs)
    """
    return plt.hist(*args, **kwargs);

def hist2d(*args, **kwargs):
    """
    Make a 2D histogram plot.
    @see matplotlib.pyplot.hist2d(x, y, bins=10, range=None, normed=False, weights=None, cmin=None, cmax=None, *, data=None, **kwargs)
    """
    return plt.hist2d(*args, **kwargs);

def hlines(*args, **kwargs):
    """
    Plot horizontal lines at each y from xmin to xmax.
    @see matplotlib.pyplot.hlines(y, xmin, xmax, colors='k', linestyles='solid', label='', *, data=None, **kwargs)
    """
    return plt.hlines(*args, **kwargs);

def imread(*args, **kwargs):
    """
    Read an image from a file into an array.
    @see matplotlib.pyplot.imread(fname, format=None)
    """
    return plt.imread(*args, **kwargs);

def imsave(*args, **kwargs):
    """
    Save an array as in image file.
    @see matplotlib.pyplot.imsave(fname, arr, **kwargs)
    """
    return plt.imsave(*args, **kwargs);

def imshow(*args, **kwargs):
    """
    Display an image, i.e.
    @see matplotlib.pyplot.imshow(X, cmap=None, norm=None, aspect=None, interpolation=None, alpha=None, vmin=None, vmax=None, origin=None, extent=None, shape=None, filternorm=1, filterrad=4.0, imlim=None, resample=None, url=None, *, data=None, **kwargs)[source]
    """
    return plt.imshow(*args, **kwargs);

def install_repl_displayhook(*args, **kwargs):
    """
    Install a repl display hook so that any stale figure are automatically redrawn when control is returned to the repl.
    @see matplotlib.pyplot.install_repl_displayhook()
    """
    return plt.install_repl_displayhook(*args, **kwargs);

def ioff(*args, **kwargs):
    """
    Turn the interactive mode off.
    @see matplotlib.pyplot.ioff()
    """
    return plt.ioff(*args, **kwargs);

def ion(*args, **kwargs):
    """
    Turn the interactive mode on.
    @see matplotlib.pyplot.ion()
    """
    return plt.ion(*args, **kwargs);

def isinteractive(*args, **kwargs):
    """
    Return the status of interactive mode.
    @see matplotlib.pyplot.isinteractive()
    """
    return plt.isinteractive(*args, **kwargs);

def legend(*args, **kwargs):
    """
    Place a legend on the axes.
    @see matplotlib.pyplot.legend(*args, **kwargs)
    """
    return plt.legend(*args, **kwargs);

def locator_params(*args, **kwargs):
    """
    Control behavior of tick locators.
    @see matplotlib.pyplot.locator_params(axis='both', tight=None, **kwargs)
    """
    return plt.locator_params(*args, **kwargs);

def loglog(*args, **kwargs):
    """
    Make a plot with log scaling on both the x and y axis.
    @see matplotlib.pyplot.loglog(*args, **kwargs)
    """
    return plt.loglog(*args, **kwargs);

def magnitude_spectrum(*args, **kwargs):
    """
    Plot the magnitude spectrum.
    @see matplotlib.pyplot.magnitude_spectrum(x, Fs=None, Fc=None, window=None, pad_to=None, sides=None, scale=None, *, data=None, **kwargs)
    """
    return plt.magnitude_spectrum(*args, **kwargs);

def margins(*args, **kwargs):
    """
    Set or retrieve autoscaling margins.
    @see matplotlib.pyplot.margins(*margins, x=None, y=None, tight=True)
    """
    return plt.margins(*args, **kwargs);

def matshow(*args, **kwargs):
    """
    Display an array as a matrix in a new figure window.
    @see matplotlib.pyplot.matshow(A, fignum=None, **kwargs)
    """
    return plt.matshow(*args, **kwargs);

def minorticks_off(*args, **kwargs):
    """
    Remove minor ticks from the axes.
    @see matplotlib.pyplot.minorticks_off()
    """
    return plt.minorticks_off(*args, **kwargs);

def minorticks_on(*args, **kwargs):
    """
    Display minor ticks on the axes.
    @see matplotlib.pyplot.minorticks_on()
    """
    return plt.minorticks_on(*args, **kwargs);

def pause(*args, **kwargs):
    """
    Pause for interval seconds.
    @see matplotlib.pyplot.pause(interval)
    """
    return plt.pause(*args, **kwargs);

def pcolor(*args, **kwargs):
    """
    Create a pseudocolor plot with a non-regular rectangular grid.
    @see matplotlib.pyplot.pcolor(*args, alpha=None, norm=None, cmap=None, vmin=None, vmax=None, data=None, **kwargs)
    """
    return plt.pcolor(*args, **kwargs);

def pcolormesh(*args, **kwargs):
    """
    Create a pseudocolor plot with a non-regular rectangular grid.
    @see matplotlib.pyplot.pcolormesh(*args, alpha=None, norm=None, cmap=None, vmin=None, vmax=None, shading='flat', antialiased=False, data=None, **kwargs)
    """
    return plt.pcolormesh(*args, **kwargs);

def phase_spectrum(*args, **kwargs):
    """
    Plot the phase spectrum.
    @see matplotlib.pyplot.phase_spectrum(x, Fs=None, Fc=None, window=None, pad_to=None, sides=None, *, data=None, **kwargs)
    """
    return plt.phase_spectrum(*args, **kwargs);

def pie(*args, **kwargs):
    """
    Plot a pie chart.
    @see matplotlib.pyplot.pie(x, explode=None, labels=None, colors=None, autopct=None, pctdistance=0.6, shadow=False, labeldistance=1.1, startangle=None, radius=None, counterclock=True, wedgeprops=None, textprops=None, center=(0, 0), frame=False, rotatelabels=False, *, data=None)
    """
    return plt.pie(*args, **kwargs);

def plot(*args, **kwargs):
    """
    Plot y versus x as lines and/or markers.
    @see matplotlib.pyplot.plot(*args, scalex=True, scaley=True, data=None, **kwargs)
    """
    return plt.plot(*args, **kwargs);

def plot_date(*args, **kwargs):
    """
    Plot data that contains dates.
    @see matplotlib.pyplot.plot_date(x, y, fmt='o', tz=None, xdate=True, ydate=False, *, data=None, **kwargs)
    """
    return plt.plot_date(*args, **kwargs);

def plotfile(*args, **kwargs):
    """
    Plot the data in a file.
    @see matplotlib.pyplot.plotfile(fname, cols=(0, ), plotfuncs=None, comments='#', skiprows=0, checkrows=5, delimiter=', ', names=None, subplots=True, newfig=True, **kwargs)
    """
    return plt.plotfile(*args, **kwargs);

def polar(*args, **kwargs):
    """
    Make a polar plot.
    @see matplotlib.pyplot.polar(*args, **kwargs)
    """
    return plt.polar(*args, **kwargs);

def psd(*args, **kwargs):
    """
    Plot the power spectral density.
    @see matplotlib.pyplot.psd(x, NFFT=None, Fs=None, Fc=None, detrend=None, window=None, noverlap=None, pad_to=None, sides=None, scale_by_freq=None, return_line=None, *, data=None, **kwargs)
    """
    return plt.psd(*args, **kwargs);

def quiver(*args, **kwargs):
    """
    Plot a 2-D field of arrows.
    @see matplotlib.pyplot.quiver(*args, data=None, **kw)
    """
    return plt.quiver(*args, **kwargs);

def quiverkey(*args, **kwargs):
    """
    Add a key to a quiver plot.
    @see matplotlib.pyplot.quiverkey(Q, X, Y, U, label, **kw)
    """
    return plt.quiverkey(*args, **kwargs);

def rc(*args, **kwargs):
    """
    Set the current rc params.
    @see matplotlib.pyplot.rc(group, **kwargs)
    """
    return plt.rc(*args, **kwargs);

def rc_context(*args, **kwargs):
    """
    Return a context manager for managing rc settings.
    @see matplotlib.pyplot.rc_context(rc=None, fname=None)
    """
    return plt.rc_context(*args, **kwargs);

def rcdefaults(*args, **kwargs):
    """
    Restore the rc params from Matplotlib's internal default style.
    @see matplotlib.pyplot.rcdefaults()
    """
    return plt.rcdefaults(*args, **kwargs);

def rgrids(*args, **kwargs):
    """
    Get or set the radial gridlines on the current polar plot.
    @see matplotlib.pyplot.rgrids(*args, **kwargs)
    """
    return plt.rgrids(*args, **kwargs);

def savefig(*args, **kwargs):
    """
    Save the current figure.
    @see matplotlib.pyplot.savefig(*args, **kwargs)
    """
    return plt.savefig(*args, **kwargs);

def sca(*args, **kwargs):
    """
    Set the current Axes instance to ax.
    @see matplotlib.pyplot.sca(ax)
    """
    return plt.sca(*args, **kwargs);

def scatter(*args, **kwargs):
    """
    A scatter plot of y vs x with varying marker size and/or color.
    @see matplotlib.pyplot.scatter(x, y, s=None, c=None, marker=None, cmap=None, norm=None, vmin=None, vmax=None, alpha=None, linewidths=None, verts=None, edgecolors=None, *, data=None, **kwargs)
    """
    return plt.scatter(*args, **kwargs);

def sci(*args, **kwargs):
    """
    Set the current image.
    @see matplotlib.pyplot.sci(im)
    """
    return plt.sci(*args, **kwargs);

def semilogx(*args, **kwargs):
    """
    Make a plot with log scaling on the x axis.
    @see matplotlib.pyplot.semilogx(*args, **kwargs)
    """
    return plt.semilogx(*args, **kwargs);

def semilogy(*args, **kwargs):
    """
    Make a plot with log scaling on the y axis.
    @see matplotlib.pyplot.semilogy(*args, **kwargs)
    """
    return plt.semilogy(*args, **kwargs);

def set_cmap(*args, **kwargs):
    """
    Set the default colormap.
    @see matplotlib.pyplot.set_cmap(cmap)
    """
    return plt.set_cmap(*args, **kwargs);

def setp(*args, **kwargs):
    """
    Set a property on an artist object.
    @see matplotlib.pyplot.setp(obj, *args, **kwargs)
    """
    return plt.setp(*args, **kwargs);

def show(*args, **kwargs):
    """
    Display a figure.
    @see matplotlib.pyplot.show(*args, **kw)
    """
    return plt.show(*args, **kwargs);

def specgram(*args, **kwargs):
    """
    Plot a spectrogram.
    @see matplotlib.pyplot.specgram(x, NFFT=None, Fs=None, Fc=None, detrend=None, window=None, noverlap=None, cmap=None, xextent=None, pad_to=None, sides=None, scale_by_freq=None, mode=None, scale=None, vmin=None, vmax=None, *, data=None, **kwargs)
    """
    return plt.specgram(*args, **kwargs);

def spy(*args, **kwargs):
    """
    Plot the sparsity pattern of a 2D array.
    @see matplotlib.pyplot.spy(Z, precision=0, marker=None, markersize=None, aspect='equal', origin='upper', **kwargs)
    """
    return plt.spy(*args, **kwargs);

def stackplot(*args, **kwargs):
    """
    Draw a stacked area plot.
    @see matplotlib.pyplot.stackplot(x, *args, data=None, **kwargs)[source]
    """
    return plt.stackplot(*args, **kwargs);

def stem(*args, **kwargs):
    """
    Create a stem plot.
    @see matplotlib.pyplot.stem(*args, linefmt=None, markerfmt=None, basefmt=None, bottom=0, label=None, data=None)
    """
    return plt.stem(*args, **kwargs);

def step(*args, **kwargs):
    """
    Make a step plot.
    @see matplotlib.pyplot.step(x, y, *args, where='pre', data=None, **kwargs)
    """
    return plt.step(*args, **kwargs);

def streamplot(*args, **kwargs):
    """
    Draw streamlines of a vector flow.
    @see matplotlib.pyplot.streamplot(x, y, u, v, density=1, linewidth=None, color=None, cmap=None, norm=None, arrowsize=1, arrowstyle='-|>', minlength=0.1, transform=None, zorder=None, start_points=None, maxlength=4.0, integration_direction='both', *, data=None)
    """
    return plt.streamplot(*args, **kwargs);

def subplot(*args, **kwargs):
    """
    Add a subplot to the current figure.
    @see matplotlib.pyplot.subplot(*args, **kwargs)
    """
    return plt.subplot(*args, **kwargs);

def subplot2grid(*args, **kwargs):
    """
    Create an axis at specific location inside a regular grid.
    @see matplotlib.pyplot.subplot2grid(shape, loc, rowspan=1, colspan=1, fig=None, **kwargs)
    """
    return plt.subplot2grid(*args, **kwargs);

def subplot_tool(*args, **kwargs):
    """
    Launch a subplot tool window for a figure.
    @see matplotlib.pyplot.subplot_tool(targetfig=None)
    """
    return plt.subplot_tool(*args, **kwargs);

def subplots(*args, **kwargs):
    """
    Create a figure and a set of subplots.
    @see matplotlib.pyplot.subplots(nrows=1, ncols=1, sharex=False, sharey=False, squeeze=True, subplot_kw=None, gridspec_kw=None, **fig_kw)
    """
    return plt.subplots(*args, **kwargs);

def subplots_adjust(*args, **kwargs):
    """
    Tune the subplot layout.
    @see matplotlib.pyplot.subplots_adjust(left=None, bottom=None, right=None, top=None, wspace=None, hspace=None)
    """
    return plt.subplots_adjust(*args, **kwargs);

def suptitle(*args, **kwargs):
    """
    Add a centered title to the figure.
    @see matplotlib.pyplot.suptitle(t, **kwargs)
    """
    return plt.suptitle(*args, **kwargs);

def switch_backend(*args, **kwargs):
    """
    Close all open figures and set the Matplotlib backend.
    @see matplotlib.pyplot.switch_backend(newbackend)
    """
    return plt.switch_backend(*args, **kwargs);

def table(*args, **kwargs):
    """
    Add a table to the current axes.
    @see matplotlib.pyplot.table(**kwargs)
    """
    return plt.table(*args, **kwargs);

def text(*args, **kwargs):
    """
    Add text to the axes.
    @see matplotlib.pyplot.text(x, y, s, fontdict=None, withdash=False, **kwargs)
    """
    return plt.text(*args, **kwargs);

def thetagrids(*args, **kwargs):
    """
    Get or set the theta gridlines on the current polar plot.
    @see matplotlib.pyplot.thetagrids(*args, **kwargs)
    """
    return plt.thetagrids(*args, **kwargs);

def tick_params(*args, **kwargs):
    """
    Change the appearance of ticks, tick labels, and gridlines.
    @see matplotlib.pyplot.tick_params(axis='both', **kwargs)
    """
    return plt.tick_params(*args, **kwargs);

def ticklabel_format(*args, **kwargs):
    """
    Change the ScalarFormatter used by default for linear axes.
    @see matplotlib.pyplot.ticklabel_format(*, axis='both', style='', scilimits=None, useOffset=None, useLocale=None, useMathText=None)
    """
    return plt.ticklabel_format(*args, **kwargs);

def tight_layout(*args, **kwargs):
    """
    Automatically adjust subplot parameters to give specified padding.
    @see matplotlib.pyplot.tight_layout(pad=1.08, h_pad=None, w_pad=None, rect=None)
    """
    return plt.tight_layout(*args, **kwargs);

def title(*args, **kwargs):
    """
    Set a title for the axes.
    @see matplotlib.pyplot.title(label, fontdict=None, loc='center', pad=None, **kwargs)
    """
    return plt.title(*args, **kwargs);

def tricontour(*args, **kwargs):
    """
    Draw contours on an unstructured triangular grid.
    @see matplotlib.pyplot.tricontour(*args, **kwargs)
    """
    return plt.tricontour(*args, **kwargs);

def tricontourf(*args, **kwargs):
    """
    Draw contours on an unstructured triangular grid.
    @see matplotlib.pyplot.tricontourf(*args, **kwargs)
    """
    return plt.tricontourf(*args, **kwargs);

def tripcolor(*args, **kwargs):
    """
    Create a pseudocolor plot of an unstructured triangular grid.
    @see matplotlib.pyplot.tripcolor(*args, **kwargs)
    """
    return plt.tripcolor(*args, **kwargs);

def triplot(*args, **kwargs):
    """
    Draw a unstructured triangular grid as lines and/or markers.
    @see matplotlib.pyplot.triplot(*args, **kwargs)
    """
    return plt.triplot(*args, **kwargs);

def twinx(*args, **kwargs):
    """
    Make a second axes that shares the x-axis.
    @see matplotlib.pyplot.twinx(ax=None)
    """
    return plt.twinx(*args, **kwargs);

def twiny(*args, **kwargs):
    """
    Make a second axes that shares the y-axis.
    @see matplotlib.pyplot.twiny(ax=None)
    """
    return plt.twiny(*args, **kwargs);

def uninstall_repl_displayhook(*args, **kwargs):
    """
    Uninstall the matplotlib display hook.
    @see matplotlib.pyplot.uninstall_repl_displayhook()
    """
    return plt.uninstall_repl_displayhook(*args, **kwargs);

def violinplot(*args, **kwargs):
    """
    Make a violin plot.
    @see matplotlib.pyplot.violinplot(dataset, positions=None, vert=True, widths=0.5, showmeans=False, showextrema=True, showmedians=False, points=100, bw_method=None, *, data=None)
    """
    return plt.violinplot(*args, **kwargs);

def vlines(*args, **kwargs):
    """
    Plot vertical lines.
    @see matplotlib.pyplot.vlines(x, ymin, ymax, colors='k', linestyles='solid', label='', *, data=None, **kwargs)
    """
    return plt.vlines(*args, **kwargs);

def xcorr(*args, **kwargs):
    """
    Plot the cross correlation between x and y.
    @see matplotlib.pyplot.xcorr(x, y, normed=True, detrend=<function detrend_none>, usevlines=True, maxlags=10, *, data=None, **kwargs)[source]
    """
    return plt.xcorr(*args, **kwargs);

def xkcd(*args, **kwargs):
    """
    Turn on xkcd sketch-style drawing mode.
    @see matplotlib.pyplot.xkcd(scale=1, length=100, randomness=2)
    """
    return plt.xkcd(*args, **kwargs);

def xlabel(*args, **kwargs):
    """
    Set the label for the x-axis.
    @see matplotlib.pyplot.xlabel(xlabel, fontdict=None, labelpad=None, **kwargs)
    """
    return plt.xlabel(*args, **kwargs);

def xlim(*args, **kwargs):
    """
    Get or set the x limits of the current axes.
    @see matplotlib.pyplot.xlim(*args, **kwargs)
    """
    return plt.xlim(*args, **kwargs);

def xscale(*args, **kwargs):
    """
    Set the x-axis scale.
    @see matplotlib.pyplot.xscale(value, **kwargs)
    """
    return plt.xscale(*args, **kwargs);

def xticks(*args, **kwargs):
    """
    Get or set the current tick locations and labels of the x-axis.
    @see matplotlib.pyplot.xticks(ticks=None, labels=None, **kwargs)
    """
    return plt.xticks(*args, **kwargs);

def ylabel(*args, **kwargs):
    """
    Set the label for the y-axis.
    @see matplotlib.pyplot.ylabel(ylabel, fontdict=None, labelpad=None, **kwargs)
    """
    return plt.ylabel(*args, **kwargs);

def ylim(*args, **kwargs):
    """
    Get or set the y-limits of the current axes.
    @see matplotlib.pyplot.ylim(*args, **kwargs)
    """
    return plt.ylim(*args, **kwargs);

def yscale(*args, **kwargs):
    """
    Set the y-axis scale.
    @see matplotlib.pyplot.yscale(value, **kwargs)
    """
    return plt.yscale(*args, **kwargs);

def yticks(*args, **kwargs):
    """
    Get or set the current tick locations and labels of the y-axis.
    @see matplotlib.pyplot.yticks(ticks=None, labels=None, **kwargs)
    """
    return plt.yticks(*args, **kwargs);
